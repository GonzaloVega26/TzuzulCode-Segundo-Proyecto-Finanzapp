package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Payment
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.relations.CardWithPayments

@Dao
interface CardDao {
    //If the fun returns a liveData element it doesn't need suspend
    //Suspend: the code can be paused or resumed
    @Insert
    suspend fun insertCard(card:Card)

    @Delete
   suspend fun deleteCard(card:Card)

    @Update
    suspend fun updateCard(card:Card)


    @Query("SELECT * FROM cards")
    fun readAllCards():LiveData<List<Card>>

    @Query("SELECT * FROM cards WHERE idCard = :idCard")
    fun readOneCard(idCard:Long):LiveData<Card>

        /* DAO FOR TRANSACTIONS*/
    @Insert
    suspend fun insertTransaction(payment : Payment)

    @Delete
    suspend fun deleteTransaction(payment : Payment)

    @Update
    suspend fun updateTransaction(payment : Payment)


    @Query("SELECT * FROM transactions")
    fun readAllTransactions():LiveData<List<Payment>>

    @Query("SELECT * FROM transactions WHERE idPayment= :idPayment")
    fun readOneTransaction(idPayment:Long):LiveData<Payment>

    @Transaction
    @Query("SELECT * FROM cards WHERE idCard = :idCard")
    fun getOneCardWithPayments(idCard: Long): List<CardWithPayments>


}