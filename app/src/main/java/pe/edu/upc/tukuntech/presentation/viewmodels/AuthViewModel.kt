package pe.edu.upc.tukuntech.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.tukuntech.data.repository.AuthRepository
import pe.edu.upc.tukuntech.data.models.RegisterRequest

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _loginResult = MutableStateFlow<Result<String>?>(null)
    val loginResult: StateFlow<Result<String>?> = _loginResult

    private val _registerResult = MutableStateFlow<Result<Unit>?>(null)
    val registerResult: StateFlow<Result<Unit>?> = _registerResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = repository.login(username, password)
        }
    }

    fun register(
        username: String,
        password: String,
        email: String,
        firstName: String,
        lastName: String
    ) {
        viewModelScope.launch {
            _registerResult.value = repository.register(username, password, email, firstName, lastName)
        }
    }
}