package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card

@Dao
interface CardDao {
    //If the fun returns a liveData element it doesn't need suspend
    //Suspend: make it asynchronous
    @Insert
    suspend fun insert(card:Card)

    @Delete
   suspend fun delete(card:Card)

    @Update
    suspend fun update(card:Card)

    @Query("SELECT * FROM card")
    fun readAll():LiveData<List<Card>>

    @Query("SELECT * FROM card WHERE id = :idCard")
    fun readOne(idCard:Long):LiveData<Card>
}