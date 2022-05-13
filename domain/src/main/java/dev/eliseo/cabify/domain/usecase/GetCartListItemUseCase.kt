package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.dto.ProductDetails
import kotlinx.coroutines.flow.Flow

interface GetCartListItemUseCase {
    suspend operator fun invoke(productCode: String): Flow<ProductDetails?>
}