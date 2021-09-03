package com.example.myapplication.room

import androidx.room.*

@Dao
interface FavouriteMovieDao {

    @Query("SELECT * FROM FavouriteMovieEntity")
    fun all(): List<FavouriteMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: FavouriteMovieEntity)

    @Update
    fun update(entity: FavouriteMovieEntity)

    @Delete
    fun delete(entity: FavouriteMovieEntity)
}