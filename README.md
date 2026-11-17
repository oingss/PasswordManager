# PasswordManager - 安卓密码管理器

一个简单干净的安卓密码管理器 App，使用 Kotlin + Material Design 开发。

## 功能特点

- ✅ 添加 / 编辑 / 删除密码条目
- ✅ 本地加密存储（使用 `EncryptedSharedPreferences`）
- ✅ 实时搜索（标题、账号、备注）
- ✅ Material Design 界面
- ✅ 密码显示/隐藏切换

## 如何运行

1. 使用 **Android Studio**（推荐 Hedgehog 或更新版本）打开本项目
2. 等待 Gradle 同步完成（首次会下载依赖）
3. 如果提示缺少图标，点击 `File → New → Image Asset` 生成默认图标即可
4. 连接真机或启动模拟器，点击 Run 运行

> 注意：项目使用了 `androidx.security:security-crypto`，最低支持 Android 7.0 (API 24)

## 项目结构

```
app/src/main/
├── java/com/example/passwordmanager/
│   ├── MainActivity.kt              # 主界面（列表 + 搜索）
│   ├── AddEditPasswordActivity.kt   # 添加/编辑界面
│   ├── PasswordAdapter.kt           # RecyclerView 适配器
│   ├── PasswordItem.kt              # 数据模型
│   └── PasswordStorage.kt           # 加密存储逻辑
└── res/
    ├── layout/
    │   ├── activity_main.xml
    │   ├── activity_add_edit.xml
    │   └── item_password.xml
    └── values/
```

## 技术栈

- Kotlin
- ViewBinding
- Material Components
- AndroidX Security Crypto（AES256 加密）
- Gson

## 后续可扩展方向

- 主密码 + 生物识别解锁
- 随机密码生成器
- 分类 / 标签功能
- 加密备份导出 / 导入
- 使用 Room + SQLCipher 替代 SharedPreferences

---

仓库地址：https://github.com/oingss/PasswordManager
