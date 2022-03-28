package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.PaymentDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Payment
import kotlinx.coroutines.launch
import java.util.*

class SendMoneyViewModel(val cardDao: CardDao, val paymentDao: PaymentDao, val idCard:Long): ViewModel() {

    val card = cardDao.readOneCard(idCard)
    val payment = Payment()
    var errorMessage: MutableLiveData<String> = MutableLiveData<String>("")

    private val _canNavigate= MutableLiveData<Boolean>()
    val canNavigate: LiveData<Boolean>
        get() = _canNavigate


    var money: String = "0"
    private val todayDate = Calendar.getInstance()
    val todayDateString get()= "Payment Date :${todayDate.get(Calendar.DAY_OF_MONTH)}/${todayDate.get(
        Calendar.MONTH)}/" +
            "${todayDate.get(Calendar.YEAR)}"
    fun sendMoney(){
        viewModelScope.launch {
            if(validateMoney()){
                payment.amountTransferred= money.toDouble()
                payment.idOfCard= idCard
                payment.destination="other"
                payment.paymentDate = todayDate
                paymentDao.insertTransaction(payment)
                updateCard()
                _canNavigate.value= true
            }else{
                errorMessage.value = "Insufficient Funds"
            }

        }
    }

    private fun updateCard(){
        viewModelScope.launch{
            card.value!!.moneyAmount -= money.toDouble()
            cardDao.updateCard(card.value!!)

        }
    }
    private fun validateMoney():Boolean{
        return card.value!!.moneyAmount >= money.toDouble()
    }

    fun onNavigated(){
        _canNavigate.value = false
    }
}