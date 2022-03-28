package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.R
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card
import kotlinx.coroutines.launch
import java.util.*


class CardCreationViewModel(val dao:CardDao): ViewModel() {
    private val _canNavigate= MutableLiveData<Boolean>()
    val canNavigate: LiveData<Boolean>
        get() = _canNavigate

    var card = Card()

    fun addCard(){
         viewModelScope.launch{

             selectBackgroundImage(card.imgTemp)
             correctDate()
              dao.insertCard(card)

             _canNavigate.value = true
         }
    }


    fun onNavigated(){
        _canNavigate.value = false
    }

    private fun selectBackgroundImage(id:Int){
        when(id){
            R.id.radio_group_value1->  card.imageBG = R.drawable.fondo_tarjeta_1
            R.id.radio_group_value2->  card.imageBG = R.drawable.fondo_tarjeta_2
            R.id.radio_group_value3->  card.imageBG = R.drawable.fondo_tarjeta_3

        }
    }

    private fun correctDate(){
        val dateArr = card.validSinceStr.split("/")
        card.validSince.set(Calendar.MONTH, dateArr[0].toInt())
        card.validSince.set(Calendar.YEAR, dateArr[1].toInt())
        card.validSince.set(Calendar.DAY_OF_MONTH, 1)

        card.validThru.set(card.validSince.get(Calendar.YEAR),card.validSince.get(Calendar.MONTH),1)
        card.validThru.add(Calendar.YEAR,5)

    }
}