package com.example.passwordmanager

data class PasswordItem(
    val id: String = java.util.UUID.randomUUID().toString(),
    var title: String,          // 网站/应用名
    var username: String,
    var password: String,
    var note: String = ""
)
