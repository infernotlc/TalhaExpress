package com.tlh.talhaexpress3.fragments.loginRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tlh.talhaexpress3.R
import com.tlh.talhaexpress3.databinding.FragmentAccountOptionsBinding


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountsOptionsFragment(): Fragment() {
    private lateinit var binding: FragmentAccountOptionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonLoginAccountOptions.setOnClickListener {
                findNavController().navigate(R.id.action_accountsOptionsFragment_to_fragmentLogin)
            }
            buttonRegisterAccountOptions.setOnClickListener{
                findNavController().navigate(R.id.action_accountsOptionsFragment_to_registerFragment)
            }

        }
    }
}