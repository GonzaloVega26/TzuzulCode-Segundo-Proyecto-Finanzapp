package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao

class CardDetailsViewModelFactory(private val dao: CardDao, private val idCard: Long): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CardDetailsViewModel::class.java)){
                val card = dao.readOneCard(idCard)

            return CardDetailsViewModel(dao, card) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}