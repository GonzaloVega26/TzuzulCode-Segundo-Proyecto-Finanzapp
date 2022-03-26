package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao
import kotlinx.coroutines.launch

class CardEditViewModel(val dao:CardDao,idCard:Long): ViewModel() {

    val card = dao.readOneCard(idCard)
    private val _navigateToAccount= MutableLiveData<Boolean>()
    val navigateToAccount: LiveData<Boolean>
        get() = _navigateToAccount

    fun update(){
        viewModelScope.launch {
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

    /*

    fun deleteTask(){
        viewModelScope.launch {
            dao.delete(task.value?.task!!)
            _navigateToList.value = true
        }
    }

 */

}