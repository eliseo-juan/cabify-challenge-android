package dev.eliseo.cabify.data.datasource

import dev.eliseo.cabify.data.mapper.ProductMapper
import dev.eliseo.cabify.data.service.ProductAPIService
import dev.eliseo.cabify.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class NetworkProductDatasource @Inject constructor(
    private val productAPIService: ProductAPIService,
) : ProductMapper {

    suspend fun getAllProducts(): Flow<List<Product>> = callbackFlow {
        trySend(productAPIService.getProducts().map { it.toProduct() })
    }
}