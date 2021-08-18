package com.example.myapplication.app

import android.app.Application
import androidx.room.Room
import com.example.myapplication.room.HistoryDao
import com.example.myapplication.room.HistoryDataBase
import com.example.myapplication.room.NoteDao
import com.example.myapplication.room.NoteDataBase


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {

        private var appInstance: App? = null
        private var historyDataBase: HistoryDataBase? = null
        private var noteDataBase: NoteDataBase? = null

        private const val DB_NAME = "DataBase.db"

        fun getHistoryDao(): HistoryDao {
            if (historyDataBase == null) {
                synchronized(HistoryDataBase::class.java) {
                    if (historyDataBase == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        historyDataBase = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            HistoryDataBase::class.java,
                            DB_NAME)
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return historyDataBase!!.historyDao()
        }


        fun getNoteDao(): NoteDao {
            if (noteDataBase == null) {
                synchronized(HistoryDataBase::class.java) {
                    if (noteDataBase == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        noteDataBase = Room.databaseBuilder(
                                appInstance!!.applicationContext,
                                NoteDataBase::class.java,
                                DB_NAME)
                                .allowMainThreadQueries()
                                .build()
                    }
                }
            }

            return noteDataBase!!.noteDao()
        }
    }
}
