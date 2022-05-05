package dev.eliseo.cabify.data.repository

import dev.eliseo.cabify.data.datasource.NetworkProductDatasource
import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.domain.repository.ProductRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

class ProductRepositoryImpl(
    private val productDataSource: NetworkProductDatasource,
) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return productDataSource.getAllProducts().firstOrNull() ?: emptyList()
    }

    override suspend fun getProduct(id: String): Product? {
        return productDataSource.getAllProducts().first().firstOrNull { it.code == id }
    }


}