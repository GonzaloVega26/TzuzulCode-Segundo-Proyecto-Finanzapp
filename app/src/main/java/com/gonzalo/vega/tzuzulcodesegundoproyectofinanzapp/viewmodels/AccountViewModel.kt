package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao

class AccountViewModel(dao:CardDao):ViewModel() {
    //val cardLiveData = dao.readAllCards()
    val cardList = dao.readAllCards()

    //This way the navigation change (complete explanation)
private val _navigateToCard = MutableLiveData<Long?>()
        val navigateToCard: LiveData<Long?>
            get()=_navigateToCard

    fun onCardClicked(idCard:Long){
        _navigateToCard.value = idCard
    }
    fun onCardNavigated(){
        _navigateToCard.value =null
    }


}