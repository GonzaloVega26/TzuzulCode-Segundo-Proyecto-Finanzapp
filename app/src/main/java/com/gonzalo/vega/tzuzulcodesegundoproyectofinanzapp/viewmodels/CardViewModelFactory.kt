package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import java.lang.IllegalArgumentException

class CardViewModelFactory(private val dao:CardDao):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CardViewModel::class.java)){
            return CardViewModel(dao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}