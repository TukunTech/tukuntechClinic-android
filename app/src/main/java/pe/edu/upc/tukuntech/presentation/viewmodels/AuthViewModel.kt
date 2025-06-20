package pe.edu.upc.tukuntech.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.tukuntech.data.models.LoginResponse
import pe.edu.upc.tukuntech.data.repository.AuthRepository
import retrofit2.Response

class AuthViewModel(private val repository: AuthRepository): ViewModel() {
    private val _loginResult = MutableStateFlow<Result<String>?>(null)
    val loginResult: StateFlow<Result<String>?> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = repository.login(username, password)
        }
    }
}
