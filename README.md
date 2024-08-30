## 环境准备与配置

1. **安装 Java 和 Neo4j**
   
   Java 和 Neo4j 均已以安装包形式提供给您。请按照项目链接 [https://github.com/MiracleTanC/Neo4j-KGBuilder](https://github.com/MiracleTanC/Neo4j-KGBuilder) 的说明，将环境变量配置好。

## 启动 Neo4j

1. 以管理员身份打开命令提示符 (cmd)。
2. 进入 Neo4j 安装文件夹：
   ```shell
   cd "C:\neo4j\neo4j-community-4.4.36\bin"

## 配置 application_dev.yml

1. 找到 `application_dev.yml` 文件，并根据您的配置修改后台和服务器、数据库链接。

   

## 打开navicat，打开kgdemo数据库



## 在kgdemo数据中导入 kg_builder.sql（第一次配置环境时才需要）

1. 打开数据库。

2. 使用您喜欢的数据库管理工具（例如 Navicat）导入 `kg_builder.sql` 文件。

   

## 启动 Neo4j 控制台

在命令提示符 (cmd) 中输入以下命令以启动 Neo4j 控制台：

shell

复制

```
neo4j.bat console
```

## 启动项目前端

1. 进入 kgBuilder-ui 文件夹。

2. 注意使用 cnpm（淘宝 NPM 镜像），因为 npm 的淘宝安全证书已过期，无法正常使用。

   终端运行

   ```
   cd kgBuilder-ui
   cnpm install
   cnpm run serve
   ```

## 服务器后端运行

运行 `personmatch_back.py` 文件和 `NER_backend.py` 文件以启动后端服务。
