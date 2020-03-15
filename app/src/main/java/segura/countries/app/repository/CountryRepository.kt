package segura.countries.app.repository

import android.util.Log
import retrofit2.HttpException
import segura.countries.app.model.Country
import segura.countries.app.model.networkHandler.Resource
import segura.countries.app.model.networkHandler.ResponseHandler
import segura.countries.app.network.RestContriesService

class CountryRepository(
    private val countryService: RestContriesService,
    private val responseHandler: ResponseHandler
) {


    suspend fun getAllCountries(): Resource<List<Country>> {
        return try {
            val response = countryService.getAllCountries()
            Log.d("repository",response.toString())
            responseHandler.handleSuccess(response)
        } catch (e: HttpException) {
            responseHandler.handleException(e)
        }
    }

}