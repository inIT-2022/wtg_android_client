package ru.sectorsj.where_to_go.auth

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import ru.sectorsj.where_to_go.model.AuthState

class WtgAppAuth private constructor(context: Context) {
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
        @Volatile
        private var instance: WtgAppAuth? = null

        fun getInstance() = synchronized(this) {
            instance ?: throw IllegalStateException("App is not initialized")
        }
        fun initAuth(context: Context) = instance ?: synchronized(this) {
            instance ?: buildAuth(context).also {
                instance = it
            }
        }
        private fun buildAuth(context: Context): WtgAppAuth = WtgAppAuth(context)
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