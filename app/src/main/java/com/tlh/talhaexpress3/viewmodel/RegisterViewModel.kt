package com.tlh.talhaexpress3.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.tlh.talhaexpress3.data.User
import com.tlh.talhaexpress3.utils.RegisterFieldState
import com.tlh.talhaexpress3.utils.RegisterValidation
import com.tlh.talhaexpress3.utils.Resource
import com.tlh.talhaexpress3.utils.validateEmail
import com.tlh.talhaexpress3.utils.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel // no requirements for viewmodelfactory and injection
 class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _register =
        MutableStateFlow<Resource<FirebaseUser>>(Resource.Unspecified()) //starting value
    val register: Flow<Resource<FirebaseUser>> =
        _register  // only readable version of register is public

    private val _validation = Channel<RegisterFieldState>()
     val validation = _validation.receiveAsFlow()

    fun createAccountWithEmailAndPassword(user: User, password: String) {
        if (checkValidation(user, password)) {
            runBlocking {
                _register.emit(Resource.Loading())
            }
            firebaseAuth.createUserWithEmailAndPassword(user.email, password)
                .addOnSuccessListener {
                    it.user?.let {
                        _register.value = Resource.Success(it)
                    }
                }.addOnFailureListener {
                    _register.value = Resource.Error(it.toString())
                }
        } else {
            val registerFieldState = RegisterFieldState(
                validateEmail(user.email),
                validatePassword(password)
            )
            runBlocking {
                _validation.send(registerFieldState)
            }
        }
    }

    private fun checkValidation(user: User, password: String): Boolean {
        val emailValidation = validateEmail(user.email)
        val passwordValidation = validatePassword(password)
        val shouldRegister =
            emailValidation is RegisterValidation.Success && passwordValidation is RegisterValidation.Success
        return shouldRegister
    }


}