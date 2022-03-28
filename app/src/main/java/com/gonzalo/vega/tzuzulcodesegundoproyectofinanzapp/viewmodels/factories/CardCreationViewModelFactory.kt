package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.CardCreationViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class CardCreationViewModelFactory(private val dao: CardDao):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CardCreationViewModel::class.java)){
            return CardCreationViewModel(dao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }

}