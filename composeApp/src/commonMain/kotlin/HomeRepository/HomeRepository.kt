package HomeRepository

import ApiClient.httpClient
import data.Products
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flow


class HomeRepository {

    suspend fun getProductsApi(): List<Products> {
        val response = httpClient.get("https://api.escuelajs.co/api/v1/products")
        return response.body()
    }

    fun getProducts() = flow {
        emit(getProductsApi())
    }

}


