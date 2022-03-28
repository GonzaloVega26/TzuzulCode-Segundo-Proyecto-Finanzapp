package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.PaymentDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.CardDetailsViewModel

@Suppress("UNCHECKED_CAST")
class CardDetailsViewModelFactory(private val dao: CardDao, private val idCard: Long,
                                  private val paymentDao: PaymentDao ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CardDetailsViewModel::class.java)){
            return CardDetailsViewModel(dao, idCard, paymentDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}