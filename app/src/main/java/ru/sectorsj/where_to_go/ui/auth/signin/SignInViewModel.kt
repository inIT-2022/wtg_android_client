package ru.sectorsj.where_to_go.ui.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sectorsj.where_to_go.model.Auth
import ru.sectorsj.where_to_go.model.AuthState
import ru.sectorsj.where_to_go.repository.signin.SignInRepository
import ru.sectorsj.where_to_go.repository.signin.SignInRepositoryImpl

class SignInViewModel: ViewModel() {
    private val repository: SignInRepository = SignInRepositoryImpl()
    val data: StateFlow<AuthState> = repository.data

    fun signIn(auth: Auth) = viewModelScope.launch {
        repository.signIn(auth)
    }
}