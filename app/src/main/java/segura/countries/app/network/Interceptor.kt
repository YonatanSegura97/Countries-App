package segura.countries.app.network

import okhttp3.Interceptor
import okhttp3.Response

class Interceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authenticationRequest = originalRequest.newBuilder().build()
        return chain.proceed(authenticationRequest)

    }


}