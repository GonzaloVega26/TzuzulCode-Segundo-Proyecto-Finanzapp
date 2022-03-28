package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.R
import java.util.Calendar

@Entity(tableName = "transactions")
data class Payment(
    @PrimaryKey(autoGenerate = true)
    var idPayment: Long = 0L,
    @ColumnInfo(name = "amount_transferred")
    var amountTransferred: Double = 0.0,
    @ColumnInfo(name = "transaction_date")
    var paymentDate: Calendar = Calendar.getInstance(),
    @ColumnInfo(name = "idOfCard") //Reference to card object
    var idOfCard: Long = 0L,
    @ColumnInfo(name="destination")
    var destination: String =""
){

    val readablePaymentDate: String
        get()= "${paymentDate.get(Calendar.MONTH)}/${paymentDate.get(Calendar.YEAR)}"


    fun getReadableMoney()= "$ $amountTransferred"

    fun setImageMoney(): Int{
        return if(destination == "self") R.drawable.green_money
        else R.drawable.red_money
    }
}