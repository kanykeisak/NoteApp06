package com.example.noteapp06.model.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp06.model.data.db.daos.NoteDao
import com.example.noteapp06.model.data.models.NoteModel


@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}