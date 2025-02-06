package com.example.noteapp06.presenter.notes

import com.example.noteapp06.App
import com.example.noteapp06.model.data.models.NoteModel

class NotePresenter(private val view: NoteContract.View) : NoteContract.Presenter {
    override fun loadNotes() {
        App.appDatabase?.noteDao()?.getAll()?.observeForever { notes ->
            view.showNotes(notes)
        }
    }
    
    override fun deleteNote(note: NoteModel) {
        App.appDatabase?.noteDao()?.delete(note)
        loadNotes()
    }
}