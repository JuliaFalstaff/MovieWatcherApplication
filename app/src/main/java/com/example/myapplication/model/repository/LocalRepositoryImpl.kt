package com.example.myapplication.model.repository

import com.example.myapplication.model.data.Movie
import com.example.myapplication.room.HistoryDao
import com.example.myapplication.room.NoteDao
import com.example.myapplication.room.NoteEntity
import com.example.myapplication.view.convertHistoryEntityToMovie
import com.example.myapplication.view.convertMovieToEntity
import com.example.myapplication.view.convertMovieToNoteEntity



class LocalRepositoryImpl(
        private val localDataSource: HistoryDao,
        private val localDataSourceNote: NoteDao) : LocalRepository {

    override fun getAllHistory(): MutableList<Movie> {
        return convertHistoryEntityToMovie(localDataSource.all())
    }


    override fun saveEntity(movie: Movie) {
        return localDataSource.insert(convertMovieToEntity(movie))
    }

    override fun getAllNotes(): List<NoteEntity> {
        return localDataSourceNote.allNotes()
    }

    override fun saveNoteEntity(id: Long, movieId: Int?, note: String?) {
        localDataSourceNote.insert(convertMovieToNoteEntity(0,movieId,note))
    }


    override fun getNoteByMovieId(movieId: Int): Int {
        return localDataSourceNote.getId(movieId)
    }








}

