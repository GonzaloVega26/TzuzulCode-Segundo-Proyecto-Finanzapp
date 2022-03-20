package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDao

import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card
import java.sql.Date

class CardViewModel(dao:CardDao):ViewModel() {
    //val cardLiveData = dao.readAll()
    val firstCard = dao.readOne(1)




}