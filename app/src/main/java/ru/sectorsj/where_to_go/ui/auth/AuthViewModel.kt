package ru.sectorsj.where_to_go.ui.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.sectorsj.where_to_go.auth.WtgAppAuth
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    auth: WtgAppAuth
) : ViewModel() {
    val data = auth.authStateFlow
    val authentificated: Boolean
        get() = data.value.email != null
}