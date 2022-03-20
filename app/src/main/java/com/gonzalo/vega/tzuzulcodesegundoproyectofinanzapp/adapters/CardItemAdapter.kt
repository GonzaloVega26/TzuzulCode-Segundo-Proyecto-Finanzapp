package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.R
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card

class CardItemAdapter: RecyclerView.Adapter<CardItemAdapter.CardItemViewHolder>() {
    var data = listOf<Card>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder:CardItemViewHolder, position: Int){
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemViewHolder = CardItemViewHolder.inflateFrom(parent)

    override fun getItemCount() = data.size


    class CardItemViewHolder(val rootView:LinearLayout):RecyclerView.ViewHolder(rootView){
        fun bind(item: Card){
            //TODO: ask how the bind is made
        }

        companion object{
            fun inflateFrom(parent: ViewGroup):CardItemViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.card_item, parent, false) as LinearLayout
                return CardItemViewHolder(view)
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