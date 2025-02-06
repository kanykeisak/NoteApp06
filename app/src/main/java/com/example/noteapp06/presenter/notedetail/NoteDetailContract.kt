package com.example.noteapp06.presenter.notedetail

import android.graphics.ColorSpace
import com.example.noteapp06.model.data.models.NoteModel

interface NoteDetailContract {
    interface View { // чтобы все показывал
        fun showNotes(notes: List<NoteModel>)
        fun showError(message: String)
    }
    
    interface Presenter { // загрузить
        fun saveNote(note: NoteModel)
        fun updateNote(note:NoteModel)
    }
}