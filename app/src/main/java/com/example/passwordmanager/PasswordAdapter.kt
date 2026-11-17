package com.example.passwordmanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.databinding.ItemPasswordBinding

class PasswordAdapter(
    private var items: MutableList<PasswordItem>,
    private val onItemClick: (PasswordItem) -> Unit,
    private val onDeleteClick: (PasswordItem) -> Unit
) : RecyclerView.Adapter<PasswordAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemPasswordBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPasswordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            tvTitle.text = item.title
            tvUsername.text = item.username
            root.setOnClickListener { onItemClick(item) }
            btnDelete.setOnClickListener { onDeleteClick(item) }
        }
    }

    override fun getItemCount() = items.size

    fun updateData(newList: MutableList<PasswordItem>) {
        items = newList
        notifyDataSetChanged()
    }
}
