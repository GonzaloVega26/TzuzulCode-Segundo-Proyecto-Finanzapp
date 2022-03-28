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
    var bankName: String ="",
    @ColumnInfo(name = "card_number")
    var cardNumber: String="",
    @ColumnInfo(name = "money_amount")
    var moneyAmount: Double = 0.0,
    @ColumnInfo(name = "owner_name")
    var owner: String="",
    @ColumnInfo(name = "valid_since")
    var validSince: Calendar = Calendar.getInstance(),
    @ColumnInfo(name = "valid_thru")
    var validThru: Calendar = Calendar.getInstance(),
    @ColumnInfo(name = "image_bg_resource")
    var imageBG: Int = 0,
    @ColumnInfo(name = "account_type")
    var accountType: Boolean = true,
    @ColumnInfo(name = "payment_red")
    var paymentRed: String = "",
    @ColumnInfo(name = "pin_code")
    var pinCode: String = ""
) {
    var imgTemp = 0

    val readableMoney: String
        get() = "$" + String.format("%.2f", moneyAmount)

    var validSinceStr = ""

fun getReadableSinceDate() = "Valid Since: \n ${validSince.get(Calendar.MONTH)}/${validSince.get(Calendar.YEAR)}"
fun getReadableThruDate() = "Valid Thru: \n ${validThru.get(Calendar.MONTH)}/${validThru.get(Calendar.YEAR)}"

//
//    val readableIdCard: String
//        get() =idCard.toString()
//
//    val readableSince get()= "${validSince.get(Calendar.MONTH)}/${validSince.get(Calendar.YEAR)}"
//    val readableThru get()= "${validThru.get(Calendar.MONTH)}/${validThru.get(Calendar.YEAR)}"
}