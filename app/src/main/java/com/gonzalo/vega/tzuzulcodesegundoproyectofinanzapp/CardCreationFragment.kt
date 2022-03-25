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
import androidx.navigation.fragment.NavHostFragment
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDatabase
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.databinding.FragmentCardCreationBinding
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card

import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.utils.MonthYearPickerDialog
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.CardCreationViewModel
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.CardCreationViewModelFactory
import java.util.*


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
        val cardCreationViewModel = ViewModelProvider(this,cardCreationViewModelFactory).get(CardCreationViewModel::class.java)
        binding.cardCreationViewModel =  cardCreationViewModel
        binding.lifecycleOwner = viewLifecycleOwner


                /*---------Data From Bundle---------*/
        val actionForm = CardCreationFragmentArgs.fromBundle(requireArguments()).action
        val idCard = CardCreationFragmentArgs.fromBundle(requireArguments()).idCard

        when(actionForm){

            "edit"-> if (idCard != null) {
                cardCreationViewModel.findCard(idCard.toLong())
            }
            "delete"-> if (idCard != null) {
                /* This way the methods for popupMenu are centralised */
               cardCreationViewModel.deleteCard(idCard.toLong())
                val navigateAction = CardCreationFragmentDirections.actionCardCreationFragmentToAccountFragment()
                container!!.findNavController().navigate(navigateAction)

            }
        }
        /*---------Listener for Custom Date Picker---------*/
        binding.validSinceEditText.setOnClickListener {
            MonthYearPickerDialog().apply {
                setListener { _, year, month, _ ->
                    binding.validSinceEditText.setText("${month+1}/$year")
                }
                show(this@CardCreationFragment.parentFragmentManager, "MonthYearPickerDialog")
            }
       }

        /*---------Listener for Adding the Card Information to DB---------*/
        binding.cardInsertionBtn.setOnClickListener {
            cardCreationViewModel.addCard(getCardDetails())
            val action = CardCreationFragmentDirections.actionCardCreationFragmentToAccountFragment()
            view.findNavController().navigate(action)
        }


        binding.radioGroup.checkedRadioButtonId
        return view
    }

    /*---------Workaround so i can use same view for editing and creating---------*/
    private fun getCardDetails():Card{
        /*---------Obtaining Date from EditText in format mm/YYYY---------*/
        val validSinceArray = binding.validSinceEditText.text.split("/")
        val validSinceCalendar = Calendar.getInstance()
        validSinceCalendar.set(validSinceArray[1].toInt(),validSinceArray[0].toInt(),1)
        val validThruCalendar = Calendar.getInstance()
        validThruCalendar.set(validSinceArray[1].toInt()+5,validSinceArray[0].toInt(),1)

        val bankName = binding.bankNameEditText.text.toString()
        val cardNumber = binding.cardNumberEditText.text.toString()
        val ownerName = binding.ownerNameEditText.text.toString()
        val pinCode = binding.pinCodeEditText.text.toString()

        /*---------Checking if id is empty so it creates new Card. else it updates Card---------*/
        val idCardParameter = binding.idCardTextView.text.toString()
        var idCard =0L
        if(!idCardParameter.isEmpty())idCard = idCardParameter.toLong()

        /*---------Checking money for Keeping the amount---------*/
        val moneyParameter = binding.moneyTextView.text.toString()
        var money =0.0
        if(moneyParameter!="$0") money = moneyParameter.subSequence(1,moneyParameter.length).toString().toDouble()
        return Card(idCard= idCard, moneyAmount=money,
            bankName = bankName, cardNumber = cardNumber, owner = ownerName,
            validSince = validSinceCalendar, validThru = validThruCalendar , pinCode = pinCode,
            accountType = true, imageBG = 1, paymentRed = "Visa")
    }



}
