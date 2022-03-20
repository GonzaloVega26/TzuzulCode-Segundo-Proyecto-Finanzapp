package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card
import kotlinx.coroutines.launch
import java.sql.Date

class CardCreationViewModel(val dao:CardDao): ViewModel() {
    var bankName = ""
    var ownerName = ""
    var cardNumber = ""
    var validSince = ""
    var validThru = ""
    var pinCode = ""


     fun addCard(){
         viewModelScope.launch{
             var validSinceArray = validSince.split("/")
             var validThruArray=validThru.split("/")
             var validSinceDate = Date(validSinceArray[0].toInt(),validSinceArray[1].toInt(),validSinceArray[2].toInt())
             var validThruDate =  Date(validThruArray[0].toInt(),validThruArray[1].toInt(),validThruArray[2].toInt())
             var card = Card(bankName = bankName, cardNumber = cardNumber, owner = ownerName, validSince = validSinceDate, validThru = validThruDate, pinCode = pinCode.toInt())
             dao.insert(card)

         }

    }

}