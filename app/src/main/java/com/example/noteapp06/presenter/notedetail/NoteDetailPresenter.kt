package com.example.noteapp06.presenter.notedetail

import com.example.noteapp06.App
import com.example.noteapp06.model.data.models.NoteModel


class NoteDetailPresenter(private val view: NoteDetailContract.View) : NoteDetailContract.Presenter {
    override fun saveNote(note: NoteModel) {
        App.appDatabase?.noteDao()?.insert(note)
        
    }
    
    override fun updateNote(note: NoteModel) {
        App.appDatabase?.noteDao()?.update(note)
    }
}