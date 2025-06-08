package org.billsplit.project.di

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun restClient() = HttpClient(CIO) {
    defaultRequest {
        // Set the base URL for all requests
        url {
            protocol = URLProtocol.HTTPS
            host = "api-new.fmb.eposapi.co.uk"
        }
        contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)

    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                co.touchlab.kermit.Logger.d(message)
            }
        }
    }
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = false
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }
}

// Koin module for dependency injection
val networkModule = module {
    single<HttpClient> { restClient() }
}
