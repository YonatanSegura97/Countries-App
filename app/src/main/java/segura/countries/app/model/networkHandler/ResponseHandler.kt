package segura.countries.app.model.networkHandler

import retrofit2.HttpException
import java.net.SocketTimeoutException


enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> Resource.error(
                "Socket Timeout exception -> $e",
                null
            )

            else -> Resource.error("This error not registered", null)
        }
    }

    private fun getErrorMessage(code: Int): String {

        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorized"
            404 -> "Not found"
            422 -> "Unprocessed Entity"
            500 -> "Internal Server Error"
            else -> "Something went wrong"
        }
    }

}