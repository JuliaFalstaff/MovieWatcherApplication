package com.example.myapplication.room

import androidx.room.*
import com.example.myapplication.model.data.Movie

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteEntity")
    fun allNotes(): List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE movieId LIKE :movieId")
    fun getNoteByMovieId(movieId: Int): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: NoteEntity)

    @Update
    fun update(entity: NoteEntity)

    @Delete
    fun delete(entity: NoteEntity)
}