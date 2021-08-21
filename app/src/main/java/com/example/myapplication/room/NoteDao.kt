package com.example.myapplication.room

import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteEntity")
    fun allNotes(): List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE movieId LIKE :movieId")
    fun getNoteByMovieId(movieId: Int): NoteEntity

    @Query("SELECT 'id' FROM NoteEntity WHERE id LIKE :id")
    fun getId(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: NoteEntity)

    @Update
    fun update(entity: NoteEntity)

    @Delete
    fun delete(entity: NoteEntity)
}

