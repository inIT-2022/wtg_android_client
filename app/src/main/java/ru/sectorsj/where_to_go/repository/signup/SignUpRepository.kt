package ru.sectorsj.where_to_go.repository.signup

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.StateFlow
import ru.sectorsj.where_to_go.dto.User
import ru.sectorsj.where_to_go.model.Registration

interface SignUpRepository {
    val data: StateFlow<User>
    suspend fun signUp(registration: Registration)
}