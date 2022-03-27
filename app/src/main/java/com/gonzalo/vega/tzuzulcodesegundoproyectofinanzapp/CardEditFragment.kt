package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.R
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDatabase
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.databinding.FragmentCardEditBinding
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.utils.MonthYearPickerDialog
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.CardEditViewModel
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.CardEditViewModelFactory

class CardEditFragment : Fragment() {

    private var _binding : FragmentCardEditBinding? = null
        private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardEditBinding.inflate(inflater,container,false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = CardDatabase.getInstance(application).cardDao
        val idCard = CardEditFragmentArgs.fromBundle(requireArguments()).idCard
        val delete = CardEditFragmentArgs.fromBundle(requireArguments()).delete

        val viewModelFactory = CardEditViewModelFactory(dao,idCard)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(CardEditViewModel::class.java)
        binding.viewModel =  viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        Log.d("aber","En Fragment $delete")


        viewModel.navigateToAccount.observe(viewLifecycleOwner, Observer{navigate ->
            if(navigate){
                val action = CardEditFragmentDirections.actionCardEditFragmentToAccountFragment()
                viewModel.onNavigatedToAccount()
                this.findNavController().navigate(action)
        }
        })

        if(delete == "delete"){
            Log.d("aber","En Fragment dentro del if $delete")
            viewModel.delete()
        }
        /*---------Listener for Custom Date Picker---------*/
        binding.validSinceEditText.setOnClickListener {
            MonthYearPickerDialog().apply {
                setListener { _, year, month, _ ->
                    val date = "${month+1}/$year"
                    binding.validSinceEditText.setText(date)
                }
                show(this@CardEditFragment.parentFragmentManager, "MonthYearPickerDialog")
            }
        }



       return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}