package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Payment

@Dao
interface PaymentDao {
    /* DAO FOR TRANSACTIONS*/
    @Insert
    suspend fun insertTransaction(payment : Payment)

    @Delete
    suspend fun deleteTransaction(payment : Payment)

    @Update
    suspend fun updateTransaction(payment : Payment)


    @Query("SELECT * FROM transactions")
    fun readAllTransactions(): LiveData<List<Payment>>

    @Query("SELECT * FROM transactions WHERE idPayment= :idPayment")
    fun readOneTransaction(idPayment:Long): LiveData<Payment>


}