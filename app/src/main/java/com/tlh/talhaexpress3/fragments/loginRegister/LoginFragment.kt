package com.tlh.talhaexpress3.fragments.loginRegister

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.tlh.talhaexpress3.R
import com.tlh.talhaexpress3.activities.ShoppingActivity
import com.tlh.talhaexpress3.databinding.FragmentLoginBinding
import com.tlh.talhaexpress3.dialog.setupBottomSheetDialog
import com.tlh.talhaexpress3.utils.Resource
import com.tlh.talhaexpress3.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvDontHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentLogin_to_registerFragment)
        }
        binding.apply {
            buttonLoginLogin.setOnClickListener {
                val email = edEmailLogin.text.toString().trim()
                val password = edPasswordLogin.text.toString() //password can have spaces
                viewModel.login(email, password)

            }
        }


        binding.tvForgotPasswordLogin.setOnClickListener {
            setupBottomSheetDialog { email ->
                viewModel.resetPassword(email)

            }
        }


        lifecycleScope.launchWhenStarted {
                viewModel.resetPassword.collect {
                    when (it) {
                        is Resource.Loading -> {
                        }

                        is Resource.Success -> {
                            Snackbar.make(
                                requireView(),
                                "Reset Link was sent to your email",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }

                        is Resource.Error -> {
                            Snackbar.make(
                                requireView(),
                                "Error: {$it.message}",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }

                        else -> Unit
                    }
                }
            }





            lifecycleScope.launchWhenStarted {
                viewModel.login.collect {
                    when (it) {
                        is Resource.Loading -> {
                            binding.buttonLoginLogin.startAnimation()
                        }

                        is Resource.Success -> {
                            binding.buttonLoginLogin.revertAnimation()
                            Intent(requireActivity(), ShoppingActivity::class.java).also { intent ->
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK) // we sure about remove activity in the backstack cause user logged in
                                startActivity(intent)
                            }
                        }

                        is Resource.Error -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                            binding.buttonLoginLogin.revertAnimation()
                        }


                        else -> Unit
                    }
                }
            }
        }

    }