package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.adapters

import androidx.recyclerview.widget.DiffUtil
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Payment

class PaymentDiffItemCallback: DiffUtil.ItemCallback<Payment>() {
    override fun areItemsTheSame(oldItem: Payment, newItem: Payment) = oldItem==newItem

    override fun areContentsTheSame(oldItem: Payment, newItem: Payment) = oldItem == newItem

}