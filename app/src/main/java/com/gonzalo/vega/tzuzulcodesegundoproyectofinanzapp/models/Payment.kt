package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "transactions")
data class Payment(
    @PrimaryKey(autoGenerate = true)
    var idPayment: Long = 0L,
    @ColumnInfo(name = "amount_transferred")
    val amountTransferred: Double,
    @ColumnInfo(name = "transaction_date")
    val transactionDate: Calendar,
    @ColumnInfo(name = "idOfCard") //Reference to card object
    val idOfCard: Long
)