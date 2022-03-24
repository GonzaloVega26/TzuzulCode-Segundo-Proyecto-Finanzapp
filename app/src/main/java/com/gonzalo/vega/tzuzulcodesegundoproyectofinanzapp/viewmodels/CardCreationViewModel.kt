package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class CardCreationViewModel(val dao:CardDao): ViewModel() {
    var pruebaCard: MutableLiveData<Card>? = null
    var prueba2Card: LiveData<Card>? = null
    var idCard = ""
    var bankName = ""
    var ownerName = ""
    var cardNumber = ""
    var validSince = ""

    var pinCode = ""


     fun addCard(){
         viewModelScope.launch{
             val validSinceArray = validSince.split("/")
             val validSinceCalendar = Calendar.getInstance()
             validSinceCalendar.set(validSinceArray[0].toInt(),validSinceArray[1].toInt(),1)
             val validThruCalendar = Calendar.getInstance()
             validThruCalendar.set(validSinceArray[0].toInt()+5,validSinceArray[1].toInt(),1)


             var card = Card(
                 bankName = bankName, cardNumber = cardNumber, owner = ownerName,
                 validSince = validSinceCalendar, validThru = validThruCalendar , pinCode = pinCode.toInt(),
             accountType = true, imageBG = 1, paymentRed = "Visa")
             dao.insertCard(card)

         }


    }

    fun editCard(idCard: Long){
        Log.d("aber", "Estoy en el editCard ${prueba2Card?.value.toString()}")
        val mediator = MediatorLiveData<Unit>()
        prueba2Card = dao.readOneCard(idCard)



        Log.d("aber", "Estoy en el editCard post ${prueba2Card?.value.toString()}")
    }





}