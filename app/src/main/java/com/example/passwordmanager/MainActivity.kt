package com.example.passwordmanager

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passwordmanager.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var storage: PasswordStorage
    private lateinit var adapter: PasswordAdapter
    private var allItems = mutableListOf<PasswordItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storage = PasswordStorage(this)
        setupRecyclerView()
        loadData()

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddEditPasswordActivity::class.java))
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun setupRecyclerView() {
        adapter = PasswordAdapter(
            mutableListOf(),
            onItemClick = { item ->
                val intent = Intent(this, AddEditPasswordActivity::class.java)
                intent.putExtra("id", item.id)
                intent.putExtra("title", item.title)
                intent.putExtra("username", item.username)
                intent.putExtra("password", item.password)
                intent.putExtra("note", item.note)
                startActivity(intent)
            },
            onDeleteClick = { item ->
                MaterialAlertDialogBuilder(this)
                    .setTitle("删除确认")
                    .setMessage("确定要删除「${item.title}」吗？")
                    .setPositiveButton("删除") { _, _ ->
                        storage.delete(item.id)
                        loadData()
                    }
                    .setNegativeButton("取消", null)
                    .show()
            }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun loadData() {
        allItems = storage.getAll()
        adapter.updateData(allItems)
    }

    private fun filter(query: String) {
        val filtered = if (query.isBlank()) {
            allItems
        } else {
            allItems.filter {
                it.title.contains(query, true) ||
                it.username.contains(query, true) ||
                it.note.contains(query, true)
            }.toMutableList()
        }
        adapter.updateData(filtered)
    }
}
