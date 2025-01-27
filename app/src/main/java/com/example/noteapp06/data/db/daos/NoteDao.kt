package com.example.noteapp06.data.db.daos

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp06.data.models.NoteModel

@Dao
interface NoteDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteModel: NoteModel)
    
    @Query("SELECT * FROM noteModel")
    fun getAll(): LiveData<List<NoteModel>>
    
    @Delete
    fun delete(noteModel: NoteModel)
    
    @Update
    fun update(noteModel: NoteModel)
    
    @Query("SELECT * FROM noteModel WHERE id = :id")
    fun getById(id: Int):NoteModel?
}