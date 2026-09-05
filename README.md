# FedBoost：基于联邦学习的 NFT 可疑交易监测与平台安全评估

> 花旗杯项目｜全员上岸队｜2023

本项目面向 NFT 交易平台的反洗钱场景，探索在**不直接交换原始交易数据**的前提下，利用联邦学习协作训练 XGBoost 风险识别模型，并将模型结果落地为可操作的交易监测、可疑用户管理、交易链条追踪和平台安全评估功能。

同时，本项目被整理为一套可用于实验教学和教学成果申报的综合案例：以真实问题为牵引，贯通数据、算法、隐私、安全、数据库、软件工程和平台开发，支持学生从单点知识练习走向完整工程实践。教学成果主文档见 [实验教学成果支撑材料](docs/teaching-award-support.md)。

## 项目亮点

- **课程知识**：将 Python 数据分析、机器学习、数据库、软件工程、Web 开发、信息安全与区块链等知识串成完整项目链路。
- **技术方法**：以 XGBoost 处理多维交易特征，以联邦学习缓解数据孤岛与隐私壁垒，以特征工程、交叉验证和指标评估验证模型效果。
- **平台成果**：基于 Spring Boot + Vue + MySQL 的前后端分离平台，提供风险监测、可疑用户、链条追踪、NFT/活动/用户数据管理等模块。
- **可复现材料**：保留 Python 原型、CSV 数据样例、Java/Vue 平台源码、SQL 初始化脚本和答辩材料。

## 项目结构

```text
.
├── algorithms/fedboost/       # XGBoost 与联邦学习原型、数据和实验结果
├── platform/                  # Spring Boot + Vue 前后端平台源码
├── docs/
│   ├── course-knowledge.md    # 课程知识与项目任务映射
│   ├── technical-method.md    # 技术路线、模型和隐私保护思路
│   ├── platform-results.md    # 平台功能、成果和局限
│   ├── reproduction.md        # 环境准备与复现步骤
│   ├── teaching-award-support.md # 评教学奖/教学成果申报支撑材料
│   ├── experimental-teaching-design.md # 实验教学实施方案与任务单
│   ├── evidence-index.md      # 申报表述与可核验证据索引
│   └── materials/             # 技术文档、商业计划书、展示材料
└── .gitignore
```

## 技术路线

```text
多平台交易数据
      ↓
数据清洗与特征处理
      ↓
各平台本地训练 XGBoost
      ↓ 仅交换模型参数/更新信息
联邦聚合得到联合模型
      ↓
可疑交易识别 → 用户筛查 → 交易链条追踪 → 平台安全评估报告
```

## 目录导航

- [课程知识映射](docs/course-knowledge.md)
- [技术方法说明](docs/technical-method.md)
- [数据、方法与主要结果](docs/data-method-results.md)
- [平台成果说明](docs/platform-results.md)
- [环境与复现](docs/reproduction.md)
- [实验教学成果支撑材料](docs/teaching-award-support.md)
- [实验教学实施方案](docs/experimental-teaching-design.md)
- [教学成果证据索引](docs/evidence-index.md)
- [原始项目材料](docs/materials/)

## 实验结果

原始实验记录给出的识别准确率为：IBM 数据集 `0.9998617170922443`，micro 数据集 `1.0`。这些结果属于项目原始实验记录；由于数据划分、特征处理、运行环境和评估流程仍需进一步标准化，不能直接视为生产环境性能承诺。

## 项目边界与后续工作

当前代码是竞赛阶段原型，真实部署前仍需补充：真实多方数据接入、严格的时间切分与类别不平衡处理、联邦安全聚合/差分隐私、模型监控与审计、合规规则配置以及端到端自动化测试。

## 致谢与许可

平台基于 RuoYi 前后端分离项目进行二次开发，原有许可信息见 `platform/LICENSE`。本仓库中的竞赛材料和新增说明用于课程教学、项目展示与技术交流。
