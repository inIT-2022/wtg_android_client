package ru.sectorsj.where_to_go.auth

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import ru.sectorsj.where_to_go.model.AuthState
import javax.inject.Inject

class WtgAppAuth @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val authStateFlow = MutableStateFlow(AuthState())

    init {
        val email = prefs.getString(emailKey, null)

        if (email == null) {
            authStateFlow.value = AuthState()
            prefs.edit().apply {
                clear()
                apply()
            }
        } else {
            authStateFlow.value = AuthState(
                email = email
            )
        }
    }

    companion object {
        @JvmStatic
        val emailKey = "email"
    }

    @Synchronized
    fun setAuth(email: String?) {
        prefs.edit().apply {
            putString(emailKey, email)
            apply()
        }
        authStateFlow.value = AuthState(
            email = email
        )
    }

    @Synchronized
    fun removeAuth() {
        prefs.edit().apply {
            clear()
            apply()
        }
        authStateFlow.value = AuthState()
    }

}