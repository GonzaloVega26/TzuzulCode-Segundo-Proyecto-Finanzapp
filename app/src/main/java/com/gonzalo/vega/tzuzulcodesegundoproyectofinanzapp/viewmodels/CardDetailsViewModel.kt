package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import androidx.lifecycle.ViewModel
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.PaymentDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card

class CardDetailsViewModel(cardDao: CardDao, idCard: Long, paymentDao : PaymentDao): ViewModel() {

    val card  = cardDao.readOneCard(idCard)

    val listPayments = paymentDao.readAllTransactionOfCard(idCard)

    private var _navigateToAddMoney = MutableLiveData<Long?>()
    val navigateToAddMoney: LiveData<Long?>
        get()=_navigateToAddMoney

    private var _navigateToSendMoney = MutableLiveData<Long?>()
    val navigateToSendMoney: LiveData<Long?>
        get()=_navigateToSendMoney

    fun onButtonAddMoneyNavigated(){
        _navigateToAddMoney.value = null
    }

    fun onButtonAddMoneyClicked(idCard:Long){
        _navigateToAddMoney.value = idCard
    }

    fun onButtonSendMoneyNavigated(){
        _navigateToSendMoney.value = null
    }

    fun onButtonSendMoneyClicked(idCard:Long){
        _navigateToSendMoney.value = idCard
    }

}