package segura.countries.app.di

import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import segura.countries.app.model.networkHandler.ResponseHandler
import segura.countries.app.network.Interceptor
import segura.countries.app.network.RestContriesService
import segura.countries.app.repository.CountryRepository
import segura.countries.app.ui.CountriesViewModel

val viewModelModule = module {
    viewModel { CountriesViewModel(get()) }
}

val restCountriesModule = module {
    single(named("country")) { provideRetrofit(get(), "https://restcountries.eu/") }
    single { get<Retrofit>(named("country")).create(RestContriesService::class.java) }
    single { provideOkHttpClient(get()) }
    single { ResponseHandler() }
    single { CountryRepository(get(), get()) }
    single { Interceptor() }
}

fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(
    interceptor: Interceptor

): OkHttpClient {
    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    okHttpClientBuilder.addInterceptor(interceptor)
    return okHttpClientBuilder.build()
}