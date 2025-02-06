package com.example.noteapp06.presenter.notes

import com.example.noteapp06.model.data.models.NoteModel

interface NoteContract {
    interface View { // чтобы все показывал
        fun showNotes(notes: List<NoteModel>)
        fun showError(message: String)
    }
    
    interface Presenter { //Загрузка
        fun loadNotes()
        fun deleteNote(note: NoteModel)
    }
}