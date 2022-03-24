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

class CardItemAdapter: ListAdapter<Card, CardItemAdapter.CardItemViewHolder>(CardDiffItemCallback()) {


    override fun onBindViewHolder(holder:CardItemViewHolder, position: Int){
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemViewHolder = CardItemViewHolder.inflateFrom(parent)



    class CardItemViewHolder(val binding:CardItemBinding,):RecyclerView.ViewHolder(binding.root),
        View.OnClickListener, PopupMenu.OnMenuItemClickListener {


        fun bind(item: Card){
            binding.card = item

            binding.imageButton.setOnClickListener {
                val action = AccountFragmentDirections.actionAccountFragmentToCardDetailsFragment(
                    binding.card!!.idCard!!
                )

                binding.root.findNavController().navigate(action)
            }
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
                    val action = AccountFragmentDirections.actionAccountFragmentToCardCreationFragment(
                        idCard=binding.card!!.idCard.toString(), action ="delete")

                    binding.root.findNavController().navigate(action)
                    true
                }
                R.id.popup_menu_edit -> {
                    val action = AccountFragmentDirections.actionAccountFragmentToCardCreationFragment(
                        idCard= binding.card!!.idCard.toString(), action="edit")

                    binding.root.findNavController().navigate(action)
                    true
                }
                else -> false
            }
        }


    }
}
