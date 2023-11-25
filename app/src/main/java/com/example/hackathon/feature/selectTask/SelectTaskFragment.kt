package com.example.hackathon.feature.selectTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hackathon.R
import com.example.hackathon.databinding.FragmentLoginBinding
import com.example.hackathon.databinding.FragmentSelectTaskBinding

class SelectTaskFragment: Fragment() {

    private var _binding: FragmentSelectTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.standart.setOnClickListener {
            findNavController().navigate(R.id.action_selectTaskFragment_to_exerciseFragment)
        }
        binding.time.setOnClickListener {
            findNavController().navigate(R.id.action_selectTaskFragment_to_exerciseFragment)
        }
        binding.EGE.setOnClickListener {
            findNavController().navigate(R.id.action_selectTaskFragment_to_exerciseFragment)
        }

        binding.TrainField.setOnClickListener {
            Toast.makeText(requireContext(), "В разработке", Toast.LENGTH_LONG).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}