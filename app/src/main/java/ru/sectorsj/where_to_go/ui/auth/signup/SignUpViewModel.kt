package ru.sectorsj.where_to_go.ui.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sectorsj.where_to_go.dto.User
import ru.sectorsj.where_to_go.model.Registration
import ru.sectorsj.where_to_go.repository.signup.SignUpRepository
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: SignUpRepository
): ViewModel() {

    val data: StateFlow<User> = repository.data

    fun signUp(registration: Registration) = viewModelScope.launch {
        repository.signUp(registration)
    }

}