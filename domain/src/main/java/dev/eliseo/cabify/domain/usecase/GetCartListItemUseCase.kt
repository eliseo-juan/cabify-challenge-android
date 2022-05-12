package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.dto.CartItem
import kotlinx.coroutines.flow.Flow

interface GetCartListItemUseCase {
    suspend operator fun invoke(productCode: String): Flow<CartItem?>
}