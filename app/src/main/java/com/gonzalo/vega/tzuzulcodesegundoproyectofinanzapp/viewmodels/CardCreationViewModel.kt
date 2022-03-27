package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.R
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card
import kotlinx.coroutines.launch


class CardCreationViewModel(val dao:CardDao): ViewModel() {
    private val _canNavigate= MutableLiveData<Boolean>()
    val canNavigate: LiveData<Boolean>
        get() = _canNavigate

    var card = Card()

    fun addCard(){
         viewModelScope.launch{
             Log.d("aber", "El id temp es ${card.imgTemp} y el BG ${card.imageBG}")
             selectBackgroundImage(card.imgTemp)
             Log.d("aber", "El id temp es ${card.imgTemp} y el BG ${card.imageBG}")

              dao.insertCard(card)
             Log.d("aber", "El id temp es ${card.imgTemp} y el BG ${card.imageBG}")
             _canNavigate.value = true
         }
    }


    fun onNavigated(){
        _canNavigate.value = false
    }

    private fun selectBackgroundImage(id:Int){
        when(id){
            R.id.radio_group_value1-> {
                Log.d("aber","Elegi el azul")
                card.imageBG = R.drawable.fondo_tarjeta_1
            }
            R.id.radio_group_value2-> {
                Log.d("aber","Elegi el rojo")
                card.imageBG = R.drawable.fondo_tarjeta_2
            }
            R.id.radio_group_value3-> {
                Log.d("aber","Elegi el violeta")
                card.imageBG = R.drawable.fondo_tarjeta_3
            }
        }
    }
}