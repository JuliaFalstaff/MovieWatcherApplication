package com.example.myapplication.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.myapplication.room.*


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        context = applicationContext
    }

    companion object {

        lateinit var context: Context

        private var appInstance: App? = null
        private var dataBase: DataBase? = null

        private const val DB_NAME = "DataBase.db"

        fun getHistoryDao(): HistoryDao {
            if (dataBase == null) {
                synchronized(DataBase::class.java) {
                    if (dataBase == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        dataBase = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            DataBase::class.java,
                            DB_NAME)
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return dataBase!!.historyDao()
        }

        fun getNoteDao(): NoteDao {
            if (dataBase == null) {
                synchronized(DataBase::class.java) {
                    if (dataBase == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        dataBase = Room.databaseBuilder(
                                appInstance!!.applicationContext,
                                DataBase::class.java,
                                DB_NAME)
                                .allowMainThreadQueries()
                                .build()
                    }
                }
            }
            return dataBase!!.noteDao()
        }
    }
}

interface IContextProvider {
    val context: Context
}

object ContextProvider : IContextProvider {
    override val context: Context
        get() = App.context
}
