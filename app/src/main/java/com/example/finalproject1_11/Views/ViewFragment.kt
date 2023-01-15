package com.example.finalproject1_11.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.finalproject1_11.R
import com.example.finalproject1_11.databinding.FragmentViewBinding

class ViewFragment : Fragment() {
    lateinit var binding : FragmentViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewBinding.inflate(layoutInflater)

        binding.mapFrag.setOnClickListener {
            findNavController().navigate(R.id.action_viewFragment_to_mapFragment)
        }
        binding.entryFrag.setOnClickListener {
            findNavController().navigate(R.id.action_viewFragment_to_entryFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}