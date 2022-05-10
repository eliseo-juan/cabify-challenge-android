package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.dto.CartItem
import kotlinx.coroutines.flow.Flow

interface GetCartListUseCase {
    suspend operator fun invoke(): Flow<List<CartItem>>
}