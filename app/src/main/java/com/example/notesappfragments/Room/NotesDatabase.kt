package com.example.notesapproom.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 2, exportSchema = false)
abstract class NotesDatabase:RoomDatabase() {

    companion object{
        @Volatile
        private var instance: NotesDatabase? = null
        fun getInstance(ctx: Context):NotesDatabase{
            if(instance != null){
                return instance as NotesDatabase
            }
            synchronized(this){
                val instance0 = Room.databaseBuilder(
                    ctx.applicationContext, NotesDatabase::class.java, "details").fallbackToDestructiveMigration().build()
                instance = instance0
                return instance as NotesDatabase
            }
        }
    }
    abstract fun NotesDao():NotesDao
}