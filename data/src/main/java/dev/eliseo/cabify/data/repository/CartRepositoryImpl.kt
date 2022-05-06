package dev.eliseo.cabify.data.repository

import dev.eliseo.cabify.data.datasource.DBCartDatasource
import dev.eliseo.cabify.data.datasource.NetworkProductDatasource
import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val productDatasource: NetworkProductDatasource,
    private val cartDatasource: DBCartDatasource
) : CartRepository {

    override suspend fun getCartProducts(): Flow<List<Product>> {
        val products = productDatasource.getAllProducts()
        return cartDatasource.getCart().map {
            it.map { cartProductCode ->
                products.first { product -> product.code == cartProductCode }
            }
        }
    }

    override suspend fun addProduct(product: Product) {
        cartDatasource.addProduct(product.code)
    }

    override suspend fun removeProduct(product: Product) {
        cartDatasource.removeProduct(product.code)
    }

}