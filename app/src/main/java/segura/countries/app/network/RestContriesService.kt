package segura.countries.app.network

import retrofit2.http.GET
import segura.countries.app.model.Country

interface RestContriesService {

    @GET("rest/v2/all")
    suspend fun getAllCountries(): List<Country>
}