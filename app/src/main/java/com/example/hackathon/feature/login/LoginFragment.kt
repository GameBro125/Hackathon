package com.example.hackathon.feature.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hackathon.R
import com.example.hackathon.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var login = ""
    private var password = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAuth.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_selectTaskFragment)
        }
        binding.loginInput.doOnTextChanged { text, _, _, _ ->
            login = text?.toString() ?: ""
            binding.buttonAuth.isEnabled = login.isNotBlank() && password.isNotBlank()
        }

        binding.passwordInput.doOnTextChanged { text, _, _, _ ->
            password = text?.toString() ?: ""
            binding.buttonAuth.isEnabled = login.isNotBlank() && password.isNotBlank()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
