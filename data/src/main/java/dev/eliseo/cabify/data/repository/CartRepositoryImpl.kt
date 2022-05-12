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
    private val cartDatasource: DBCartDatasource
) : CartRepository {

    override suspend fun getCartProducts(): Flow<List<String>> {
        return cartDatasource.getCart()
    }

    override suspend fun addProduct(productCode: String) {
        cartDatasource.addProduct(productCode)
    }

    override suspend fun removeProduct(productCode: String) {
        cartDatasource.removeProduct(productCode)
    }

}