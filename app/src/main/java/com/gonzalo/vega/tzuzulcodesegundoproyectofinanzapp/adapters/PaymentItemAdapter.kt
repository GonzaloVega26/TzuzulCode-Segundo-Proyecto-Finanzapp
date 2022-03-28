package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.databinding.PaymentItemBinding
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Payment


class PaymentItemAdapter : ListAdapter<Payment, PaymentItemAdapter.PaymentItemViewHolder>(PaymentDiffItemCallback()) {

    override fun onBindViewHolder(holder: PaymentItemViewHolder, position: Int) {
       val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaymentItemViewHolder = PaymentItemViewHolder.inflateFrom(parent)

    class PaymentItemViewHolder(val binding: PaymentItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item : Payment){
            binding.payment = item
        }

        companion object{
            fun inflateFrom(parent: ViewGroup):PaymentItemViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PaymentItemBinding.inflate(layoutInflater,parent, false)
                return PaymentItemViewHolder(binding)
            }
        }


    }




}
