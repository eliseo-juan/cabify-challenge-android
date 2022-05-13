package dev.eliseo.cabify.data.repository

import dev.eliseo.cabify.data.datasource.DBCartDatasource
import dev.eliseo.cabify.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDatasource: DBCartDatasource
) : CartRepository {

    override suspend fun getCartProducts(): Flow<List<String>> {
        return cartDatasource.getCart()
    }

    override suspend fun addProduct(productCode: String) {
        cartDatasource.addProduct(productCode)
    }

    override suspend fun addProducts(productCode: String, quantity: Int) {
        cartDatasource.addProducts(productCode, quantity)
    }

    override suspend fun removeProduct(productCode: String) {
        cartDatasource.removeProduct(productCode)
    }

}