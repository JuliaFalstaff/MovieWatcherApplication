package com.example.myapplication.model.repository

import com.example.myapplication.model.data.Movie
import com.example.myapplication.room.HistoryDao
import com.example.myapplication.room.HistoryEntity
import com.example.myapplication.room.NoteDao
import com.example.myapplication.room.NoteEntity
import com.example.myapplication.view.convertHistoryEntityToMovie
import com.example.myapplication.view.convertMovieToEntity
import com.example.myapplication.view.convertMovieToNoteEntity
import com.example.myapplication.view.convertNoteEntityToMovie


class LocalRepositoryImpl(
        private val localDataSource: HistoryDao,
        private val localDataSourceNote: NoteDao) : LocalRepository {

    override fun getAllHistory(): MutableList<Movie> {
        return convertHistoryEntityToMovie(localDataSource.all())
    }


    override fun saveEntity(movie: Movie) {
        return localDataSource.insert(convertMovieToEntity(movie))
    }

    override fun getAllNotes(): MutableList<Movie> {
        return convertNoteEntityToMovie(localDataSourceNote.allNotes())
    }

    override fun saveNoteEntity(movieId: Int, title: String, backdrop_path: String, note: String) {
        return localDataSourceNote.insert(convertMovieToNoteEntity(movieId, title, backdrop_path, note))
    }

    override fun getNoteByMovieId(movieId: Int) : List<NoteEntity> {
        return localDataSourceNote.getNoteByMovieId(movieId)
    }
}