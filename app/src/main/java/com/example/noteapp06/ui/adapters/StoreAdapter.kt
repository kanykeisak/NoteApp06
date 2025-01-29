package com.example.noteapp06.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp06.data.models.NoteModel
import com.example.noteapp06.databinding.ItemStoreBinding
import com.example.noteapp06.ui.adapters.NoteAdapter.DiffCallback

class StoreAdapter: ListAdapter<String, StoreAdapter.ViewHolder>(DiffCallback()){
    class ViewHolder(private val binding: ItemStoreBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String?) {
            binding.txtStore.text = item
        }
        
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class DiffCallback() : DiffUtil.ItemCallback<String>(){
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
        
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}