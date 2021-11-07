package com.example.notesappfragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesappfragments.Room.NotesRepository
import com.example.notesapproom.Room.Notes
import com.example.notesapproom.Room.NotesDatabase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class myViewModel(activity: Application): AndroidViewModel(activity){
    private val notesArray: LiveData<List<Notes>>
    private val repoNote: NotesRepository

    init{
        val notesDao = NotesDatabase.getInstance(activity).NotesDao()
        repoNote = NotesRepository(notesDao)
        notesArray = repoNote.Notes
    }
    fun getNotesList(): LiveData<List<Notes>> {
        return notesArray
    }
    fun insertNote(note: Notes){
        GlobalScope.launch(IO) {
            repoNote.insertNote(note)
        }
    }
    fun updateNote(note: Notes){
        GlobalScope.launch(IO) {
            repoNote.updateNote(note)
        }
    }
    fun deleteNote(note: Notes){
        GlobalScope.launch(IO) {
            repoNote.deleteNote(note)
        }
    }
}