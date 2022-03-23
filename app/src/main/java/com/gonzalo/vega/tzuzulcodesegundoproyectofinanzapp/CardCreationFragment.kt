package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDatabase
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.databinding.FragmentCardCreationBinding
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.utils.DatePickerFragment
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.CardCreationViewModel
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.CardCreationViewModelFactory
import java.sql.Date


class CardCreationFragment : Fragment() {
    private var _binding: FragmentCardCreationBinding? = null
    private val binding get() =_binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardCreationBinding.inflate(inflater, container,false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = CardDatabase.getInstance(application).cardDao
        val cardCreationViewModelFactory = CardCreationViewModelFactory(dao)
        val cardCreationViewModel = ViewModelProvider(this,cardCreationViewModelFactory).get(CardCreationViewModel::class.java)
        binding.cardCreationViewModel =  cardCreationViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.cardInsertionBtn.setOnClickListener {
            cardCreationViewModel.addCard()
            val action = CardCreationFragmentDirections.actionCardCreationFragmentToAccountFragment()
            view.findNavController().navigate(action)
        }
        binding.validSinceEditText.setOnClickListener {
            showDatePickerDialog()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        getFragmentManager()?.let { datePicker.show(it, "datePicker") }
    }
    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.validSinceEditText.setText("$year/$month/$day")
    }

}
