package dev.eliseo.cabify.data.repository

import dev.eliseo.cabify.data.datasource.NetworkProductDatasource
import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: NetworkProductDatasource,
) : ProductRepository {

    private var products: List<Product> = emptyList()

    override suspend fun getProducts(): List<Product> {

        return products.takeIf { it.isNotEmpty() } ?: productDataSource.getAllProducts().also {
            products = it
        }
    }

    override suspend fun getProduct(id: String): Product? {
        return getProducts().firstOrNull { it.code == id }
    }
}