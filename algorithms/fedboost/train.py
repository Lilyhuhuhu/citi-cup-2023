# 导入必要的库
import xgboost as xgb
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score, roc_auc_score
from xgboost import plot_tree
from sklearn.model_selection import learning_curve
import os
os.environ["PATH"] += os.pathsep + 'E:/anaconda\Lib\site-packages\graphviz'

# 加载数据集
data = pd.read_csv('./transactions_micro.csv')
data = data.drop(columns=['tran_id','alert_id','tran_timestamp'])
data = data.fillna(method='ffill')
# 特征工程
x = data.iloc[:500,:-1]
y = data.iloc[:500,-1]
# TODO: 对数据进行清洗、特征提取等操作

# 划分训练集和测试集
X_train, X_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=42)

params = {
    'booster':'gbtree',
    'objective':'multi:softmax',
    'num_class':2,
    'gamma':0.1,
    'max_depth':3,
    'lambda':2,
    'subsample':0.7,
    'colsample_bytree':0.7,
    'min_child_weight':3,
    'silent':0,
    'eta':0.1,
    'seed':1000,
    'nthread':4,
}
plst = list(params.items())

dtrain = xgb.DMatrix(X_train,y_train,enable_categorical=True)
num_rounds = 500
# xgboost模型训练
model = xgb.train(plst,dtrain,num_rounds)
# plot_tree(model,fmap='', num_trees=0, rankdir='UT', ax=None)
# plt.show() 
# 对测试集进行预测
dtest = xgb.DMatrix(X_test,enable_categorical=True)
y_pred = model.predict(dtest)

# 得到学习曲线数据
train_sizes, train_scores, test_scores = learning_curve(
    model,
    X_train,
    y_train,
    train_sizes=[50, 80, 110],
    cv=5,
    scoring="neg_mean_squared_error",
    n_jobs=-1,
)

# 计算平均值与标准差
train_scores_mean = -np.mean(train_scores, axis=1)
train_scores_std = -np.std(train_scores, axis=1)
test_scores_mean = -np.mean(test_scores, axis=1)
test_scores_std = -np.std(test_scores, axis=1)

# 绘制学习曲线图
plt.figure(figsize=(8, 6))
plt.title("Learning Curve (XGBoost)")
plt.fill_between(
    train_sizes,
    train_scores_mean - train_scores_std,
    train_scores_mean + train_scores_std,
    alpha=0.1,
    color="r",
)
plt.fill_between(
    train_sizes,
    test_scores_mean - test_scores_std,
    test_scores_mean + test_scores_std,
    alpha=0.1,
    color="b",
)
plt.plot(
    train_sizes,
    train_scores_mean,
    "o-",
    color="r",
    label="Training score",
)
plt.plot(
    train_sizes,
    test_scores_mean,
    "o-",
    color="b",
    label="Cross-validation score",
)
plt.legend(loc="best")

plt.xlabel("# Training examples")
plt.ylabel("Score")
plt.show()

# 训练模型
# model.fit(X_train, y_train)

# 在测试集上进行预测
# y_pred = model.predict(X_test)

# 模型评估
accuracy = accuracy_score(y_test, y_pred)
roc_auc = roc_auc_score(y_test, y_pred)
print("Accuracy:", accuracy)
print("ROC AUC:", roc_auc)

# 可视化输出
xgb.plot_importance(model, importance_type='gain')
plt.show()