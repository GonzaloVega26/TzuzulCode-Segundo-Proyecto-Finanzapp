package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.R
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import kotlinx.coroutines.launch

class CardEditViewModel(val dao:CardDao,idCard:Long): ViewModel() {

    val card = dao.readOneCard(idCard)
    private val _navigateToAccount= MutableLiveData<Boolean>()
    val navigateToAccount: LiveData<Boolean>
        get() = _navigateToAccount

    fun update(){
        viewModelScope.launch {
            selectBackgroundImage(card.value!!.imgTemp)
        dao.updateCard(card.value!!)
            _navigateToAccount.value = true
        }
    }

    fun delete(){
        viewModelScope.launch {
            Log.d("aber","En VM ${card.value!!}")
            dao.deleteCard((card.value!!))
            _navigateToAccount.value = true
        }
    }

    fun onNavigatedToAccount(){
        _navigateToAccount.value = false
    }

    private fun selectBackgroundImage(id:Int){
        when(id){
            R.id.radio_group_value1-> {
                Log.d("aber","Elegi el azul")
                card.value!!.imageBG = R.drawable.fondo_tarjeta_1
            }
            R.id.radio_group_value2-> {
                Log.d("aber","Elegi el rojo")
                card.value!!.imageBG = R.drawable.fondo_tarjeta_2
            }
            R.id.radio_group_value3-> {
                Log.d("aber","Elegi el violeta")
                card.value!!.imageBG = R.drawable.fondo_tarjeta_3
            }
        }
    }

}