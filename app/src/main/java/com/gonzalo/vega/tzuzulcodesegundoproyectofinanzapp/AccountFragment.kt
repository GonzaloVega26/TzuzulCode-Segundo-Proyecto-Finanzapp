package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp

import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.adapters.CardItemAdapter
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDatabase
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.databinding.FragmentAccountBinding
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.CardViewModel
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.CardViewModelFactory

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = CardDatabase.getInstance(application).cardDao
        val viewModelFactory = CardViewModelFactory(dao)
        val viewModel= ViewModelProvider(this,viewModelFactory).get(CardViewModel::class.java)
        binding.cardViewModel =  viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.createBtn.setOnClickListener {
           val action = AccountFragmentDirections.actionAccountFragmentToCardCreationFragment()
            view.findNavController().navigate(action)

        }

        val adapter = CardItemAdapter{idCard->viewModel.onCardClicked(idCard)}

        binding.cardList.adapter = adapter
        viewModel.cardList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.navigateToCard.observe(viewLifecycleOwner,Observer{
            it?.let {
                val action = AccountFragmentDirections.actionAccountFragmentToCardDetailsFragment(it)
                viewModel.onCardNavigated()
                this.findNavController().navigate(action)
            }
        })



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerForContextMenu(binding.cardList);
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}