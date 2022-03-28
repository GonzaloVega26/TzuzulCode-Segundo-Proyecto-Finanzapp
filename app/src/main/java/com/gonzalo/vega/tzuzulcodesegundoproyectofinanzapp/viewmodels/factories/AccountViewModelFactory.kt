package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.factories


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.AccountViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class AccountViewModelFactory(private val dao:CardDao):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AccountViewModel::class.java)){
            return AccountViewModel(dao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}