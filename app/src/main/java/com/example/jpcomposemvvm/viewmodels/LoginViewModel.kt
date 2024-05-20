import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jpcomposemvvm.models.UserRepository
import com.example.jpcomposemvvm.models.data.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun login(email: String, password: String, onResult: (Result<LoginResponse>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.login(email, password, onResult)
        }
    }
}
