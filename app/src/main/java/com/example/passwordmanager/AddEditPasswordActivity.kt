package com.example.passwordmanager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordmanager.databinding.ActivityAddEditBinding

class AddEditPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEditBinding
    private lateinit var storage: PasswordStorage
    private var editId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storage = PasswordStorage(this)

        // 判断是编辑还是新增
        editId = intent.getStringExtra("id")
        if (editId != null) {
            binding.etTitle.setText(intent.getStringExtra("title"))
            binding.etUsername.setText(intent.getStringExtra("username"))
            binding.etPassword.setText(intent.getStringExtra("password"))
            binding.etNote.setText(intent.getStringExtra("note"))
            supportActionBar?.title = "编辑密码"
        } else {
            supportActionBar?.title = "添加密码"
        }

        binding.btnSave.setOnClickListener { save() }
        binding.btnCancel.setOnClickListener { finish() }
    }

    private fun save() {
        val title = binding.etTitle.text.toString().trim()
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString()
        val note = binding.etNote.text.toString().trim()

        if (title.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "标题、账号和密码不能为空", Toast.LENGTH_SHORT).show()
            return
        }

        if (editId != null) {
            val item = PasswordItem(editId!!, title, username, password, note)
            storage.update(item)
        } else {
            storage.add(PasswordItem(title = title, username = username, password = password, note = note))
        }
        finish()
    }
}
