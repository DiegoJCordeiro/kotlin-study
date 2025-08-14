package br.com.diegocordeiro.kotlinstudy.config.ktor

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable

fun Application.configureErrorHandler() {
    install(StatusPages) {
        exception<Exception> { call, cause ->
            val statusCode = HttpStatusCode.InternalServerError
            val error = ExceptionError(
                statusCode = statusCode.value,
                message = cause.message ?: "Internal Server Error"
            )
            call.respond(status = HttpStatusCode.InternalServerError, message=  error)

        }
    }
}

@Serializable
data class ExceptionError(
    val statusCode: Int,
    val message: String
)