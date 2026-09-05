# 导入必要的库
import xgboost as xgb
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score, roc_auc_score
from xgboost import plot_tree
from sklearn.model_selection import learning_curve,GridSearchCV 
import os
import seaborn as sb
os.environ["PATH"] += os.pathsep + 'E:/anaconda\Lib\site-packages\graphviz'

# 加载数据集
data = pd.read_csv('./transactions.csv')
data = data.drop(columns=['TX_ID','TX_TYPE','ALERT_ID'])
data = data.fillna(method='ffill')
# 特征工程
x = data.iloc[:,:-1]
y = data.iloc[:,-1]
# TODO: 对数据进行清洗、特征提取等操作

# 划分训练集和测试集
X_train, X_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=42)

params = {
    'booster':'gbtree',
    'objective':'multi:softmax',
    'num_class':2,
    'gamma':0.1,
    'max_depth':3,
    'learning_rate':0.2,
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
cv_params = {'n_estimators':[3,4,5,6],'max_depth':[5,6,7,8]}
other_params = {'learning_rate':0.2, 'min_child_weight': 1, 'seed': 0,
                    'subsample': 0.8, 'colsample_bytree': 0.8, 'gamma': 0, 'reg_alpha': 0, 'reg_lambda': 1}
model1 = xgb.XGBClassifier(**other_params)
optimized_GBM = GridSearchCV(estimator=model1, param_grid=cv_params, scoring='accuracy', cv=5, verbose=1, n_jobs=4)
optimized_GBM.fit(X_train, y_train)
means = optimized_GBM.cv_results_['mean_test_score']
print('means',means)
#print('每轮迭代运行结果:{0}'.format(evalute_result))
print('参数的最佳取值：{0}'.format(optimized_GBM.best_params_))
print('最佳模型得分:{0}'.format(optimized_GBM.best_score_))
grid_visualization = np.array(means)
grid_visualization.shape = (4,4)
means.shape = (4,4)
sb.heatmap(grid_visualization,cmap='Blues')
plt.xticks(np.arange(4)+0.5,optimized_GBM.param_grid['n_estimators'])
plt.yticks(np.arange(4)+0.5,optimized_GBM.param_grid['max_depth'])
plt.xlabel('n_estimators')
plt.ylabel('max_depth')
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