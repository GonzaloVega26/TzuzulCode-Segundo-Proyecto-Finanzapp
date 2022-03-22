package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDatabase
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.databinding.FragmentCardDetailsBinding
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.CardDetailsViewModel
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.CardDetailsViewModelFactory

class CardDetailsFragment : Fragment() {
    private var _binding: FragmentCardDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardDetailsBinding.inflate(inflater,container, false)

    val idCard= CardDetailsFragmentArgs.fromBundle(requireArguments()).idCard
    val view = binding.root
    val application = requireNotNull(this.activity).application
    val dao = CardDatabase.getInstance(application).cardDao
    val viewModelFactory = CardDetailsViewModelFactory(dao,idCard)
    val viewModel = ViewModelProvider(this, viewModelFactory).get(CardDetailsViewModel::class.java)



        binding.viewModel=viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return view
    }

}