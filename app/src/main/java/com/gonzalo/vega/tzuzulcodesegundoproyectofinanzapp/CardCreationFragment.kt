package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDatabase
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.databinding.FragmentCardCreationBinding

import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.utils.MonthYearPickerDialog
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.CardCreationViewModel
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.CardCreationViewModelFactory


class CardCreationFragment : Fragment() {
    private var _binding: FragmentCardCreationBinding? = null
    private val binding get() =_binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardCreationBinding.inflate(inflater, container,false)
        val action = CardCreationFragmentArgs.fromBundle(requireArguments()).action
        val idCard = CardCreationFragmentArgs.fromBundle(requireArguments()).idCard
        Log.d("aber", "Estoy en el metodo $action y el id es $idCard")
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = CardDatabase.getInstance(application).cardDao
        val cardCreationViewModelFactory = CardCreationViewModelFactory(dao)
        val cardCreationViewModel = ViewModelProvider(this,cardCreationViewModelFactory).get(CardCreationViewModel::class.java)
        binding.cardCreationViewModel =  cardCreationViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        //val idCard= CardDetailsFragmentArgs.fromBundle(requireArguments()).idCard
        binding.cardInsertionBtn.setOnClickListener {
            cardCreationViewModel.addCard()
            val action = CardCreationFragmentDirections.actionCardCreationFragmentToAccountFragment()
            view.findNavController().navigate(action)
        }
        var a = 0;
        when(action){

            "edit"-> if (idCard != null) {
                Log.d("aber", "Estoy en el when if")
                cardCreationViewModel.editCard(idCard.toLong())
            }else{Log.d("aber", "Estoy en el when else")

            }
                "create"-> a=0
            "edit"-> a=0
        }

        binding.validSinceEditText.setOnClickListener {
            MonthYearPickerDialog().apply {
                setListener { view, year, month, dayOfMonth ->
                    binding.validSinceEditText.setText("$year/${month+1}")
                }
                show(this@CardCreationFragment.parentFragmentManager, "MonthYearPickerDialog")
            }
       }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }




}
