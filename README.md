
## 环境准备

- Node.js >= 14.x
- npm 或 yarn
- JDK >= 8
- Maven >= 3.6
- MySQL >= 5.7

## 安装与运行

### 1. 克隆项目

```bash
git clone https://github.com/yourusername/moyunreading-master.git
cd moyunreading-master
```

### 2. 数据库初始化

- 创建数据库（如 `moyunreading`），导入 `backend/code/init.sql` 或 `backend/code/src/main/resources/db/init.sql`。
- 如有数据库迁移需求，可执行 `db/migration` 下的 SQL 脚本。

### 3. 启动后端

```bash
cd backend/code
mvn clean install
# 修改 application.yml 数据库配置
mvn spring-boot:run
```

### 4. 启动前端

```bash
cd ../../
npm install
npm run serve
# 或
yarn install
yarn serve
```

前端默认运行在 [http://localhost:8080](http://localhost:8080)，后端接口默认在 [http://localhost:8081](http://localhost:8081)（端口以实际配置为准）。


如需详细文档或有任何疑问，欢迎联系项目维护者。
