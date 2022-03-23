package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Payment

data class CardWithPayments(
    @Embedded val card: Card,
    @Relation(
        parentColumn = "idCard",
        entityColumn = "idOfCard"
    )
    val listOfPayments: List<Payment>
)