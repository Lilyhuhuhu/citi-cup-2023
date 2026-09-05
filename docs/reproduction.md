# 环境与复现

## 算法原型

建议使用独立 Python 虚拟环境。原始脚本面向竞赛时期依赖，版本兼容性可能需要按本机环境调整。

```bash
cd algorithms/fedboost
python -m venv .venv
source .venv/bin/activate
pip install numpy pandas scikit-learn xgboost matplotlib seaborn
python train.py
```

`train.py` 使用 `transactions_micro.csv`；`grid_searcher.py` 使用 `transactions.csv`。运行前请根据当前 XGBoost 版本检查 `enable_categorical`、目标函数和数据标签格式。

## 平台源码

```bash
cd platform
mvn clean package
```

前端位于 `platform/ruoyi-ui`，后端入口位于 `platform/ruoyi-admin`。运行平台前需要准备 Java、Maven、Node.js、MySQL 和 Redis，并按 `platform/sql/` 中脚本初始化数据库；具体环境说明见 `platform/doc/`。

## 数据说明

数据样例保存在 `algorithms/fedboost/data/`。原始下载地址和提取信息见 `docs/dataset-link.txt`。真实部署时请替换为经过授权、脱敏并符合监管要求的数据，不要将生产数据提交到仓库。
