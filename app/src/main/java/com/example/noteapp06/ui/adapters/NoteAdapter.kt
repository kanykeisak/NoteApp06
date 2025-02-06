package com.example.noteapp06.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp06.model.data.models.NoteModel
import com.example.noteapp06.databinding.ItemNoteBinding
import com.example.noteapp06.ui.interfaces.OnClickItem
import com.example.noteapp06.ui.adapters.NoteAdapter.ViewHolder as ViewHolder1

class NoteAdapter(
    private val onLongClick: OnClickItem,
    private val onClick: OnClickItem
): ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallback()) {
    class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.txtTitle.text = item.title
            binding.txtDescription.text = item.description
//            binding.tvDate.text = item.date
//            binding.tvTime.text = item.time
        }
        
    }
    
    override fun onBindViewHolder(holder: ViewHolder1, position: Int) {
        holder.bind(getItem(position))
        
        holder.itemView.setOnLongClickListener {
            onLongClick.onLongClick(getItem(position))
            true
        }
        
        holder.itemView.setOnClickListener {
            onClick.onClick(getItem(position))
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder1 {
        return ViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        ))
    }
    
    class DiffCallback() : DiffUtil.ItemCallback<NoteModel>(){
        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }
    }
}