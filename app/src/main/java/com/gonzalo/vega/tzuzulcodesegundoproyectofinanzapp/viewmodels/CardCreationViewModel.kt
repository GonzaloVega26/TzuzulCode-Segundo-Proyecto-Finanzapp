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
    private val _canNavigate= MutableLiveData<Boolean>()
    val canNavigate: LiveData<Boolean>
        get() = _canNavigate

    var card = Card()

    fun addCard(){
         viewModelScope.launch{
             Log.d("aber", "$card" )

              dao.insertCard(card)
             _canNavigate.value = true
         }
    }


    fun onNavigated(){
        _canNavigate.value = false
    }

}