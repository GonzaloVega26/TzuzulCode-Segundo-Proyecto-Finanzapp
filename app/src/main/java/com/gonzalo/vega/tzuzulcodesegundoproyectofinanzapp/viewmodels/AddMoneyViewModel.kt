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

class AddMoneyViewModel(val paymentDao: PaymentDao,val cardDao: CardDao, val idCard:Long): ViewModel() {
    private val _canNavigate= MutableLiveData<Boolean>()
    val canNavigate: LiveData<Boolean>
        get() = _canNavigate

    var payment = Payment()
    val card = cardDao.readOneCard(idCard)

    var money: String = "0"
    val todayDate = Calendar.getInstance()
    val todayDateString get()= "Payment Date :${todayDate.get(Calendar.DAY_OF_MONTH)}/${todayDate.get(Calendar.MONTH)}/" +
            "${todayDate.get(Calendar.YEAR)}"
    fun addMoney(){
        viewModelScope.launch {
            Log.d("aber","El dinero es $money")
            payment.amountTransferred=money.toDouble()
            payment.idOfCard= idCard
            payment.destination="self"
            payment.paymentDate = todayDate
            Log.d("aber","El dinero es $payment")
            paymentDao.insertTransaction(payment)

            updateCard()


            _canNavigate.value= true
        }
    }

    fun updateCard(){
        viewModelScope.launch{
            Log.d("aber", "La tarjeta es ${card.value}")
        card.value!!.moneyAmount += money.toDouble()
        cardDao.updateCard(card.value!!)

        }
    }

    fun onNavigated(){
        _canNavigate.value = false
    }
}