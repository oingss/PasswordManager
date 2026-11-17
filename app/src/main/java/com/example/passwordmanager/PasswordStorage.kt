package com.example.passwordmanager

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PasswordStorage(context: Context) {
    private val gson = Gson()
    private val prefs = EncryptedSharedPreferences.create(
        context,
        "password_prefs",
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun getAll(): MutableList<PasswordItem> {
        val json = prefs.getString("passwords", "[]") ?: "[]"
        val type = object : TypeToken<MutableList<PasswordItem>>() {}.type
        return gson.fromJson(json, type) ?: mutableListOf()
    }

    fun saveAll(list: List<PasswordItem>) {
        prefs.edit().putString("passwords", gson.toJson(list)).apply()
    }

    fun add(item: PasswordItem) {
        val list = getAll()
        list.add(item)
        saveAll(list)
    }

    fun update(item: PasswordItem) {
        val list = getAll()
        val index = list.indexOfFirst { it.id == item.id }
        if (index != -1) {
            list[index] = item
            saveAll(list)
        }
    }

    fun delete(id: String) {
        val list = getAll().filter { it.id != id }.toMutableList()
        saveAll(list)
    }
}
