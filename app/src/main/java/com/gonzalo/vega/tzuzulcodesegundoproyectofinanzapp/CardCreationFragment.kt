package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
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
                /*---------Setting the Fragment Bindings---------*/
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = CardDatabase.getInstance(application).cardDao
        val cardCreationViewModelFactory = CardCreationViewModelFactory(dao)
        val viewModel = ViewModelProvider(this,cardCreationViewModelFactory).get(CardCreationViewModel::class.java)
        binding.viewModel =  viewModel
        binding.lifecycleOwner = viewLifecycleOwner



        /*---------Listener for Custom Date Picker---------*/
        binding.validSinceEditText.setOnClickListener {
            MonthYearPickerDialog().apply {
                setListener { _, year, month, _ ->
                    val date = "${month+1}/$year"
                    binding.validSinceEditText.setText(date)
                }
                show(this@CardCreationFragment.parentFragmentManager, "MonthYearPickerDialog")
            }
       }

        /*---------Observer for Navigation---------*/
        viewModel.canNavigate.observe(viewLifecycleOwner, Observer { navigate->
            if(navigate){
                val action = CardCreationFragmentDirections.actionCardCreationFragmentToAccountFragment()
                viewModel.onNavigated()
                view.findNavController().navigate(action)
            }

        })


        binding.radioGroup.checkedRadioButtonId
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
