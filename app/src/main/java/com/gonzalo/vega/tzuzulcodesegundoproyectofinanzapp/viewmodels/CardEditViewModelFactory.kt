package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import java.lang.IllegalArgumentException

class CardEditViewModelFactory(private val dao: CardDao, private val idCard:Long): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardEditViewModel::class.java)) {
            return CardEditViewModel(dao, idCard) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }

}