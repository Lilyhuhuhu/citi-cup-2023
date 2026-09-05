import syft as sy
import numpy as np
import xgboost as xgb
from sklearn.metrics import accuracy_score

# 创建虚拟工作节点
hook = sy.TorchHook(torch)
bob = sy.VirtualWorker(hook, id="bob")
alice = sy.VirtualWorker(hook, id="alice")
workers = [bob, alice]

# 创建本地数据
local_X = np.random.rand(100, 10)
local_y = np.random.randint(2, size=100)

# 将本地数据分发给工作节点
data = [(torch.tensor(local_X[i]), torch.tensor(local_y[i])) for i in range(len(local_X))]
data_ptr = [datum.send(worker) for datum, worker in zip(data, workers)]

# 初始化XGBoost模型
bst = xgb.XGBClassifier()

# 训练模型
for round in range(10):
    print("Round:", round)
    # 分别对每个工作节点的数据进行训练
    models = []
    for data, worker in zip(data_ptr, workers):
        # 将数据移动到工作节点上
        X, y = data.get()
        X, y = X.numpy(), y.numpy()
        # 使用XGBoost训练模型
        model = xgb.XGBClassifier()
        model.fit(X, y)
        models.append(model)
    # 对每个工作节点的模型进行平均化
    avg_model = xgb.XGBClassifier()
    avg_params = {}
    for key in models[0].get_xgb_params().keys():
        avg_params[key] = np.mean([model.get_xgb_params()[key] for model in models])
    avg_model.set_params(**avg_params)
    # 在本地对平均化后的模型进行训练
    X_local, y_local = torch.tensor(local_X), torch.tensor(local_y)
    bst.fit(X_local, y_local)
    # 在测试集上评估模型性能
    test_X, test_y = np.random.rand(50, 10), np.random.randint(2, size=50)
    y_pred_local = bst.predict(test_X)
    y_pred_federated = sy.FederatedDataLoader([data_ptr[i].get() for i in range(len(workers))]).federated_map(
        lambda x, y, model: torch.tensor(model.predict(x.numpy())), models)
    y_pred_federated = y_pred_federated.get().numpy()
    acc_local = accuracy_score(test_y, y_pred_local)
    acc_federated = accuracy_score(test_y, y_pred_federated)
    print("Local accuracy:", acc_local)
    print("Federated accuracy:", acc_federated)
