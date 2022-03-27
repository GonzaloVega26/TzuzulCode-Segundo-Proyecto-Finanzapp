package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.PaymentDao

class AddMoneyViewModelFactory(private val paymentDao: PaymentDao,private val cardDao: CardDao,
                               private val idCard: Long):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddMoneyViewModel::class.java)) {
            return AddMoneyViewModel(paymentDao, cardDao, idCard) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
