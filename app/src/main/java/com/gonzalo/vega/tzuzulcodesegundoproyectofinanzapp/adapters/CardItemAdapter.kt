package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.AccountFragmentDirections
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.R
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.databinding.CardItemBinding
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card

class CardItemAdapter(private val clickListener:(id:Long)->Unit): ListAdapter<Card, CardItemAdapter.CardItemViewHolder>(CardDiffItemCallback()) {


    override fun onBindViewHolder(holder:CardItemViewHolder, position: Int){
        val item = getItem(position)
        holder.bind(item,clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemViewHolder = CardItemViewHolder.inflateFrom(parent)



    class CardItemViewHolder(val binding:CardItemBinding):RecyclerView.ViewHolder(binding.root),
        View.OnClickListener, PopupMenu.OnMenuItemClickListener {


        fun bind(item: Card, clickListener:(id:Long)->Unit){
            binding.card = item
            binding.imageButton.setImageResource(item.imageBG)
            binding.imageButton.setOnClickListener {clickListener(item.idCard)}
            binding.optionMenuBtn.setOnClickListener(this)


        }

        companion object{
            fun inflateFrom(parent: ViewGroup):CardItemViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CardItemBinding.inflate(layoutInflater, parent, false)

                return CardItemViewHolder(binding)
            }
        }

        override fun onClick(view: View) {
          val  popUpMenu = PopupMenu(view.context,view)
            popUpMenu.inflate(R.menu.pop_up_menu)
            popUpMenu.setOnMenuItemClickListener(this)
            popUpMenu.show()
        }

        override fun onMenuItemClick(item: MenuItem): Boolean {
            return when(item.itemId){
                R.id.popup_menu_delete ->{

                    true
                }
                R.id.popup_menu_edit -> {
                    val action = AccountFragmentDirections.actionAccountFragmentToCardEditFragment(
                         binding.card!!.idCard
                    )

                    binding.root.findNavController().navigate(action)
                    true
                }
                else -> false
            }
        }


    }
}
