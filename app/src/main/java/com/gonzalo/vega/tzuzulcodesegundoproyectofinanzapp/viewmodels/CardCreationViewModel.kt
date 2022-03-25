package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.R
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class CardCreationViewModel(val dao:CardDao): ViewModel() {

    var sourceCard: LiveData<Card>? = null



    fun addCard(card:Card){
         viewModelScope.launch{
              dao.insertCard(card)
         }
    }

    fun deleteCard(idCard:Long){
        viewModelScope.launch{
            dao.deleteCardById(idCard)
        }
    }

    fun findCard(idCard: Long){
        sourceCard = dao.readOneCard(idCard)
    }

    fun detectRadioButon(id:Int){
        when(id){

        }
    }

}