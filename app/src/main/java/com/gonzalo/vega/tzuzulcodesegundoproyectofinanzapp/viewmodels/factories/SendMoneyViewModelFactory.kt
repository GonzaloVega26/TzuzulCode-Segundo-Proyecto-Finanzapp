package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.PaymentDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.AddMoneyViewModel
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.SendMoneyViewModel

@Suppress("UNCHECKED_CAST")
class SendMoneyViewModelFactory(private val cardDao: CardDao, private val paymentDao: PaymentDao,
                                private val idCard:Long): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SendMoneyViewModel::class.java)) {
            return SendMoneyViewModel(cardDao,paymentDao,idCard) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}