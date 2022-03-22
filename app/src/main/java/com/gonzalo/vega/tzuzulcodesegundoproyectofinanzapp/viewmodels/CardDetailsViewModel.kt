package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import androidx.lifecycle.ViewModel
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import android.util.Log
import androidx.lifecycle.LiveData
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card

class CardDetailsViewModel(dao: CardDao, card: LiveData<Card>): ViewModel() {

    val card  = card


}