package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val idCard: Long,
    val name: String,
    val number: String

) {
}