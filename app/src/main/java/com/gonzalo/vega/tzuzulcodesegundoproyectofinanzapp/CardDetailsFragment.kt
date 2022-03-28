package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.adapters.PaymentItemAdapter
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.CardDatabase
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.databinding.FragmentCardDetailsBinding
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.CardDetailsViewModel
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.viewmodels.factories.CardDetailsViewModelFactory

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
    val cardDao = CardDatabase.getInstance(application).cardDao
    val paymentDao = CardDatabase.getInstance(application).paymentDao
    val viewModelFactory = CardDetailsViewModelFactory(cardDao,idCard, paymentDao)
    val viewModel = ViewModelProvider(this, viewModelFactory).get(CardDetailsViewModel::class.java)

        binding.viewModel=viewModel
        binding.lifecycleOwner = viewLifecycleOwner

//        /* This way the image backgrounds loads when the livedata has value*/
//       viewModel.card.observe(viewLifecycleOwner,Observer{
//           binding.imageButton.setImageResource(it.imageBG)
//       })

        viewModel.navigateToAddMoney.observe(viewLifecycleOwner,Observer{
            it?.let {
                val action = CardDetailsFragmentDirections.actionCardDetailsFragmentToAddMoneyFragment(it)
                viewModel.onButtonAddMoneyNavigated()
                this.findNavController().navigate(action)
            }
        })

        viewModel.navigateToSendMoney.observe(viewLifecycleOwner,Observer{
            it?.let {
                val action = CardDetailsFragmentDirections.actionCardDetailsFragmentToSendMoneyFragment(it)
                viewModel.onButtonSendMoneyNavigated()
                this.findNavController().navigate(action)
            }
        })

        val adapter = PaymentItemAdapter()

        binding.paymentList.adapter = adapter

        viewModel.listPayments.observe(viewLifecycleOwner, Observer{
            adapter.submitList(it)
        })

        viewModel.card.observe(viewLifecycleOwner, Observer { card->
            if(!card.accountType){
                binding.expandCreditBtn.visibility = View.GONE

            }
        })
        return view
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}