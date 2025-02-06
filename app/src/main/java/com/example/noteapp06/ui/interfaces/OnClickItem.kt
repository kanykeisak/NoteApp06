package com.example.noteapp06.ui.interfaces

import com.example.noteapp06.model.data.models.NoteModel

interface OnClickItem {
    
    fun onLongClick(noteModel: NoteModel)
    
    fun onClick(noteModel: NoteModel)
}