package dev.eliseo.cabify.data.datasource

import dev.eliseo.cabify.data.mapper.ProductMapper
import dev.eliseo.cabify.data.service.ProductAPIService
import dev.eliseo.cabify.domain.model.Product
import javax.inject.Inject

class NetworkProductDatasource @Inject constructor(
    private val productAPIService: ProductAPIService,
) : ProductMapper {

    suspend fun getAllProducts(): List<Product> =
        productAPIService.getProducts().products.map { it.toProduct() }
}