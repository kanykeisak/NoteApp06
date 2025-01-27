package com.example.noteapp06.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteapp06.data.models.NoteModel

@Dao
interface NoteDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteModel: NoteModel)
    
    @Query("SELECT * FROM noteModel")
    fun getAll(): LiveData<List<NoteModel>>
}