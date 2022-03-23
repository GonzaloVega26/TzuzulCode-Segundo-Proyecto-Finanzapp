package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.utils.DatePickerFragment
import kotlinx.coroutines.launch
import java.util.*

class CardCreationViewModel(val dao:CardDao): ViewModel() {
    var bankName = ""
    var ownerName = ""
    var cardNumber = ""
    var validSince = ""
    var validThru = ""
    var pinCode = ""


     fun addCard(){
         viewModelScope.launch{
             val validSinceArray = validSince.split("/")
             val validSinceCalendar = Calendar.getInstance()
             validSinceCalendar.set(validSinceArray[0].toInt(),validSinceArray[1].toInt(),validSinceArray[2].toInt())
             val validThruCalendar = validSinceCalendar
             validThruCalendar.add(Calendar.YEAR,5)

             var card = Card(
                 bankName = bankName, cardNumber = cardNumber, owner = ownerName,
                 validSince = validSinceCalendar, validThru = validThruCalendar , pinCode = pinCode.toInt(),
             accountType = true, imageBG = 1, paymentRed = "Visa")
             dao.insertCard(card)

         }

    }





}