package ru.sectorsj.where_to_go.ui.auth

import androidx.lifecycle.ViewModel
import ru.sectorsj.where_to_go.auth.WtgAppAuth

class AuthViewModel : ViewModel() {
    val data = WtgAppAuth.getInstance().authStateFlow
    val authentificated: Boolean
        get() = data.value.email != null
}