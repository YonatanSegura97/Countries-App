package segura.countries.app.ui

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import segura.countries.app.model.networkHandler.Resource
import segura.countries.app.repository.CountryRepository

class CountriesViewModel(
    private val repository: CountryRepository,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        //Handle network error here
    }
    val countriesList = liveData(coroutineScope.coroutineContext + exceptionHandler) {
        emit(Resource.loading(null))
        emit(repository.getAllCountries())
    }


    override fun onCleared() {
        super.onCleared()
        coroutineScope.coroutineContext.cancel()
    }
}