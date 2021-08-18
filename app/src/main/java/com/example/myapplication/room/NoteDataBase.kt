package com.example.myapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 2, exportSchema = false)
abstract class NoteDataBase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}