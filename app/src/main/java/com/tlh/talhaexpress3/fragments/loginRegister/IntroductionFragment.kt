package com.tlh.talhaexpress3.fragments.loginRegister

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tlh.talhaexpress3.R
import com.tlh.talhaexpress3.activities.ShoppingActivity
import com.tlh.talhaexpress3.databinding.FragmentIntroductionBinding
import com.tlh.talhaexpress3.viewmodel.IntroductionViewModel
import com.tlh.talhaexpress3.viewmodel.IntroductionViewModel.Companion.SHOPPING_ACTIVITY
import com.tlh.talhaexpress3.viewmodel.IntroductionViewModel.Companion.ACCOUNT_OPTIONS_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IntroductionFragment : Fragment() {
    private lateinit var binding: FragmentIntroductionBinding
    private val viewModel by viewModels<IntroductionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroductionBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.navigate.collect {
                when (it) {
                    SHOPPING_ACTIVITY -> {
                        Intent(requireActivity(), ShoppingActivity::class.java).also { intent ->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK) // we sure about remove activity in the backstack cause user logged in
                            startActivity(intent)
                        }
                    }
                    ACCOUNT_OPTIONS_FRAGMENT -> {
                        findNavController().navigate(it)
                    }
                    else -> Unit
                }
            }
        }


        binding.buttonStart.setOnClickListener {
            viewModel.startButtonClick()
          findNavController().navigate(R.id.action_introductionFragment_to_accountsOptionsFragment)
        }
    }
}