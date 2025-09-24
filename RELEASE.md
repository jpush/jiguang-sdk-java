# 自动发布配置说明

本文档说明如何配置和使用自动发布流程，将 `jiguang-sdk` 自动发布到 Maven Central。

## 配置步骤

### 1. 配置 GitHub Secrets

在 GitHub 仓库的 Settings > Secrets and variables > Actions 中添加以下 secrets：

#### Maven Central 相关
- `OSSRH_USERNAME`: Sonatype OSSRH 用户名
- `OSSRH_TOKEN`: Sonatype OSSRH 密码或令牌

#### GPG 签名相关
- `GPG_PRIVATE_KEY`: GPG 私钥（完整的 ASCII 格式）

> **注意**：当前配置使用无密码 GPG 密钥，因此不需要配置 `GPG_PASSPHRASE`

### 2. GPG 密钥生成和配置

如果还没有 GPG 密钥，请按照以下步骤生成：

```bash
# 当前使用方案：生成无密码的 GPG 密钥对
gpg --batch --generate-key <<EOF
Key-Type: RSA
Key-Length: 4096
Name-Real: JPush Release
Name-Email: support@jiguang.cn
Expire-Date: 2y
%no-protection
%commit
EOF

# 查看生成的密钥
gpg --list-secret-keys --keyid-format LONG

# 导出私钥（用于 GPG_PRIVATE_KEY）
gpg --armor --export-secret-keys YOUR_KEY_ID

# 导出公钥并上传到密钥服务器
gpg --armor --export YOUR_KEY_ID
gpg --send-keys YOUR_KEY_ID --keyserver keys.openpgp.org
```

### 3. Maven Central 账户配置

确保已经在 Sonatype OSSRH 注册并验证了域名 `io.github.jpush`。

## 使用方法

### 发布新版本

1. **更新版本号**：
   ```bash
   # 更新根目录和 jiguang-sdk 模块的版本号
   mvn versions:set -DnewVersion=5.2.5 -DgenerateBackupPoms=false
   mvn versions:update-child-modules -DgenerateBackupPoms=false
   ```

2. **提交更改**：
   ```bash
   git add .
   git commit -m "release: 准备发布版本 5.2.5"
   git push origin main
   ```

3. **打标签触发发布**：
   ```bash
   git tag v5.2.5
   git push origin v5.2.5
   ```

### 自动化流程

当推送标签后，GitHub Actions 会自动执行以下步骤：

1. ✅ 检出代码
2. ✅ 设置 JDK 8 环境
3. ✅ 配置 GPG 签名
4. ✅ 从标签获取版本号
5. ✅ 更新项目版本号
6. ✅ 编译和测试 jiguang-sdk 模块
7. ✅ 使用 release profile 发布到 Maven Central
8. ✅ 发布结果通知

## 验证发布

发布成功后，可以通过以下方式验证：

1. **检查 Maven Central**：
   访问 https://central.sonatype.com/artifact/io.github.jpush/jiguang-sdk 查看新版本

2. **本地测试**：
   ```xml
   <dependency>
       <groupId>io.github.jpush</groupId>
       <artifactId>jiguang-sdk</artifactId>
       <version>5.2.5</version>
   </dependency>
   ```

## 注意事项

### 版本规范
- 标签格式：`v{version}`（如 `v5.2.5`）
- 版本号遵循语义化版本规范：`MAJOR.MINOR.PATCH`

### 发布限制
- 只发布 `jiguang-sdk` 模块，不包含示例项目
- 自动签名并上传源码包和文档包
- 发布后自动释放到 Maven Central（约需 10-30 分钟同步）

### 故障排查
- 检查 GitHub Actions 日志
- 确认所有 secrets 配置正确
- 验证 GPG 密钥和 Maven Central 权限

## 手动发布（备用方案）

如果自动发布失败，可以使用手动方式：

```bash
# 本地发布到 Maven Central
mvn clean deploy -P release -pl jiguang-sdk -DskipTests

# 或者先部署到暂存区
mvn clean deploy -P release -pl jiguang-sdk -DskipTests -DaltDeploymentRepository=ossrh::default::https://s01.oss.sonatype.org/content/repositories/snapshots
```

---

📝 **重要提醒**：首次配置时，请先在测试分支验证流程，确保所有配置正确后再在主分支使用。
