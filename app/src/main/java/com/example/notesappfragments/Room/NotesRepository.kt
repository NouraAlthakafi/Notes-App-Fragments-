package com.example.notesappfragments.Room

import androidx.lifecycle.LiveData
import com.example.notesapproom.Room.Notes
import com.example.notesapproom.Room.NotesDao

class NotesRepository(private val notesDao: NotesDao) {
    val Notes: LiveData<List<Notes>> = notesDao.getAllNotesInfo()
    suspend fun insertNote(note: Notes){
        notesDao.insertNote(note)
    }
    suspend fun updateNote(note: Notes){
        notesDao.updateNote(note)
    }

    suspend fun deleteNote(note: Notes){
        notesDao.deleteNote(note)
    }
}