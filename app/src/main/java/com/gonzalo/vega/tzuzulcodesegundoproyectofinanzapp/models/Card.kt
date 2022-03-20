package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "cards")
data class Card(
    @PrimaryKey(autoGenerate = true)
    var idCard: Long=0L,
    @ColumnInfo(name = "bank_name")
    var bankName: String = "",
    @ColumnInfo(name = "card_number")
    var cardNumber: String = "",
    @ColumnInfo(name = "money_amount")
    var moneyAmount: Double = 0.0,
    @ColumnInfo(name = "owner_name")
    var owner: String = "",
    @ColumnInfo(name = "valid_since")
    var validSince: Date = Date(0),
    @ColumnInfo(name = "valid_thru")
    var validThru: Date = Date(0),
    @ColumnInfo(name = "pin_code")
    var pinCode: Int = 0
)