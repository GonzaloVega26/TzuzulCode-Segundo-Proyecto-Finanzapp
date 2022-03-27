package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Payment
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.relations.CardWithPayments

@Dao
interface CardDao {
    //If the fun returns a liveData element it doesn't need suspend
    //Suspend: the code can be paused or resumed
    @Insert
    suspend fun insertCard(card:Card)

    @Update
    suspend fun updateCard(card:Card)

    @Delete
   suspend fun deleteCard(card:Card)

    @Query("DELETE FROM cards WHERE idCard= :idCard")
    suspend fun deleteCardById(idCard:Long)

    @Query("SELECT * FROM cards")
    fun readAllCards():LiveData<List<Card>>

    @Query("SELECT * FROM cards WHERE idCard = :idCard")
    fun readOneCard(idCard:Long):LiveData<Card>




    @Transaction
    @Query("SELECT * FROM cards WHERE idCard = :idCard")
    fun getOneCardWithPayments(idCard: Long): List<CardWithPayments>


}