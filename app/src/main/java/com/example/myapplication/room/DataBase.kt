package com.example.myapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class, NoteEntity::class], version = 2, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun historyDao() : HistoryDao
    abstract fun noteDao(): NoteDao
}