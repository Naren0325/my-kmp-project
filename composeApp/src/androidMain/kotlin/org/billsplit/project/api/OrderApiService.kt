package org.billsplit.project.api

import com.iggloo.hrs.b2b.common.ApiError
import com.iggloo.hrs.b2b.common.ApiResult
import com.iggloo.hrs.b2b.common.parseErrorMessage
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.billsplit.project.model.GetOrderRequest
import org.billsplit.project.model.GetOrderResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OrderApiService : KoinComponent {

    private val httpClient: HttpClient by inject()

    suspend fun getOrderByTableId(tableId: Int): ApiResult<GetOrderResponse> {
        return try {
            val response: HttpResponse = httpClient.post("/getOrderByTableId") {
                contentType(ContentType.Application.Json)
                setBody(GetOrderRequest(apiKey = "3444CED48713B3DA2EC075CDDE7D562F", tableId = tableId))
            }

            if (response.status.isSuccess()) {
                val body = response.body<GetOrderResponse>()
                ApiResult.Success(body)
            } else {
                val errorText = response.bodyAsText()
                val errorCode = response.status.value
                ApiResult.Failure(ApiError(errorCode, parseErrorMessage(errorText)))
            }
        } catch (e: Exception) {
            ApiResult.Failure(ApiError(1500, "An error occurred: ${e.message}"))
        }
    }
}