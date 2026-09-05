# FedBoost 风险管理平台

这是 FedBoost 项目的业务平台部分，基于 RuoYi 前后端分离框架二次开发。

## 技术栈

- 后端：Spring Boot、Spring Security、JWT、MyBatis、MySQL、Redis
- 前端：Vue、Element UI
- 工程结构：`ruoyi-admin`、`ruoyi-nft`、`ruoyi-system`、`ruoyi-ui` 等模块

## 项目功能

- 风险监测与可疑交易管理
- 可疑用户查询、详情、导出和维护
- 交易链条追踪页面
- NFT、活动、用户等业务实体管理
- 登录认证、角色权限、操作日志和后台管理能力

## 运行说明

请先准备 Java、Maven、Node.js、MySQL 和 Redis，并执行 `sql/` 下的初始化脚本。完整的项目背景、技术方法和复现建议见仓库根目录 `README.md` 以及 `docs/`。

`README-RUOYI.md` 保留了原始框架说明，便于了解二次开发基础。
