package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "cards")
data class Card(
    @PrimaryKey(autoGenerate = true)
    var idCard: Long = 0L,

    @ColumnInfo(name = "bank_name")
    val bankName: String,
    @ColumnInfo(name = "card_number")
    val cardNumber: String,
    @ColumnInfo(name = "money_amount")
    val moneyAmount: Double = 0.0,
    @ColumnInfo(name = "owner_name")
    val owner: String,
    @ColumnInfo(name = "valid_since")
    val validSince: Calendar,
    @ColumnInfo(name = "valid_thru")
    val validThru: Calendar,
    @ColumnInfo(name = "image_bg_resource")
    val imageBG: Int,
    @ColumnInfo(name = "account_type")
    val accountType: Boolean,
    @ColumnInfo(name = "payment_red")
    val paymentRed: String,
    @ColumnInfo(name = "pin_code")
    var pinCode: String = "0"
) {


    val readableMoney: String
        get() = "$" + String.format("%.2f", moneyAmount)

    fun converDateToString(date : Calendar): String{
        if(date == null){
            return ""
        }else return "${date.get(Calendar.MONTH)}/${date.get(Calendar.YEAR)}"
    }

    val readableIdCard: String
        get() =idCard.toString()
}