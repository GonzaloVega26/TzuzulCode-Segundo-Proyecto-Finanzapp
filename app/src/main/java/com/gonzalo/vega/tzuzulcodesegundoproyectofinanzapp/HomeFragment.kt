package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.databinding.FragmentHomeBinding



class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val btn = binding.root.findViewById<Button>(R.id.card_creation_btn)
//        btn.setOnClickListener {
//
//            //val action = HomeFragmentDirections
//            //binding.root.findNavController().navigate(action)
//        }
    }

}