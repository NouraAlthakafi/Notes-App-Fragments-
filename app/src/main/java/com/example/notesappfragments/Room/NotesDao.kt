package com.example.notesapproom.Room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes ORDER BY id ASC")
    fun getAllNotesInfo(): LiveData<List<Notes>>

    @Insert
    suspend fun insertNote(note: Notes)

    @Update
    suspend fun updateNote(note: Notes)

    @Delete
    suspend fun deleteNote(note: Notes)
}