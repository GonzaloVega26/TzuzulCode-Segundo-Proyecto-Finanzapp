package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.LinearLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.R
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.databinding.CardItemBinding
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card

class CardItemAdapter: ListAdapter<Card, CardItemAdapter.CardItemViewHolder>(CardDiffItemCallback()) {


    override fun onBindViewHolder(holder:CardItemViewHolder, position: Int){
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemViewHolder = CardItemViewHolder.inflateFrom(parent)



    class CardItemViewHolder(val binding:CardItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Card){
            binding.card = item
        }

        companion object{
            fun inflateFrom(parent: ViewGroup):CardItemViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CardItemBinding.inflate(layoutInflater, parent, false)
                return CardItemViewHolder(binding)
            }
        }

    }
}

/*
class TaskItemAdapter: Adapter<TaskItemAdapter.TaskItemViewHolder>() {
    var data = listOf<Task>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }



    class TaskItemViewHolder(val rootView: TextView):RecyclerView.ViewHolder(rootView){

        fun bind(item:Task){
            rootView.text = item.name
        }

        companion object{
            fun inflateFrom(parent: ViewGroup):TaskItemViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.task_item,parent,false) as TextView
                return TaskItemViewHolder(view)
            }
        }
    }
}
 */