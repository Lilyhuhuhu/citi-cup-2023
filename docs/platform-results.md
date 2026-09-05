# 平台成果

## 平台定位

平台在 RuoYi 前后端分离框架基础上进行二次开发，后端采用 Spring Boot、Spring Security、JWT、MyBatis 和 MySQL，前端采用 Vue 与 Element UI，形成可扩展的风险管理后台。

## 已实现模块

- **风险监测**：提供风险数据列表、筛选和后台管理入口；
- **可疑用户**：对高风险用户进行查询、详情查看、导出和维护；
- **交易链条追踪**：展示与可疑交易关联的用户/交易关系，辅助溯源；
- **NFT / 活动 / 用户管理**：对应业务实体的增删改查、分页和权限控制；
- **平台基础能力**：登录认证、动态菜单、角色权限、操作日志、定时任务和代码生成。

## 代码落点

- 后端业务模块：`platform/ruoyi-nft/`
- 风险与用户接口：`platform/ruoyi-admin/src/main/java/com/ruoyi/web/controller/nft/`
- 风险页面：`platform/ruoyi-ui/src/views/plat/risk/`
- 可疑用户与链条页面：`platform/ruoyi-ui/src/views/user/illeguser/`
- 数据库脚本：`platform/sql/`

## 成果价值

项目完成了从“算法想法”到“业务平台”的转化：模型负责发现异常，平台负责承接告警、用户管理和关系追踪，安全评估负责把单笔交易风险上升到平台治理视角。该结构适合继续接入真实数据源、规则引擎和模型服务。
