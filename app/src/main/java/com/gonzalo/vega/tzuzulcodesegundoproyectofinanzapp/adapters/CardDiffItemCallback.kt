package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.adapters

import androidx.recyclerview.widget.DiffUtil
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card

class CardDiffItemCallback : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card) = oldItem==newItem

    override fun areContentsTheSame(oldItem: Card, newItem: Card) = oldItem == newItem

}