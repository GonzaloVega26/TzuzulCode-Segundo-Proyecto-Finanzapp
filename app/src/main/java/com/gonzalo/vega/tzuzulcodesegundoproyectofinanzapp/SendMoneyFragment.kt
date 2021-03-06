package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDatabase
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.databinding.FragmentSendMoneyBinding
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.SendMoneyViewModel
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.factories.SendMoneyViewModelFactory

class SendMoneyFragment : Fragment() {
    private var _binding: FragmentSendMoneyBinding? = null
    private val binding get()= _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSendMoneyBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val cardDao = CardDatabase.getInstance(application).cardDao
        val paymentDao = CardDatabase.getInstance(application).paymentDao
        val idCard = AddMoneyFragmentArgs.fromBundle(requireArguments()).idCard
        val viewModelFactory = SendMoneyViewModelFactory(cardDao,paymentDao,idCard)
        val viewModel =  ViewModelProvider(this, viewModelFactory).get(SendMoneyViewModel::class.java)
        binding.viewModel= viewModel
        binding.lifecycleOwner= viewLifecycleOwner


        viewModel.canNavigate.observe(viewLifecycleOwner, Observer{navigate->
            if(navigate){
                val action = SendMoneyFragmentDirections.actionSendMoneyFragmentToCardDetailsFragment(idCard)
                viewModel.onNavigated()
                this.findNavController().navigate(action)
            }
        })

        return view
    }


}