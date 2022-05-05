package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetProductWithDiscountsListUseCaseImpl : GetProductWithDiscountsListUseCase {
    override fun invoke(): Flow<Map<Product, Discount?>> = flow {
        emit(
            mapOf(
                Product(
                    "VOUCHER",
                    "Cabify Voucher",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    5.0,
                    currencyCode = "EUR",
                    imageUrl = "https://brandemia.org/sites/default/files/inline/images/cabify_logo_nuevo_2.png"
                ) to Discount.TakeXGetY(2, 1),
                Product(
                    "TSHIRT",
                    "Cabify T-Shirt",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    20.0,
                    currencyCode = "EUR",
                    imageUrl = "https://brandemia.org/sites/default/files/inline/images/cabify_logo_nuevo_2.png"
                ) to Discount.DiscountByAmount(3, 0.05f),
                Product(
                    "MUG",
                    "Cabify Coffee Mug",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    7.50,
                    currencyCode = "EUR",
                    imageUrl = "https://brandemia.org/sites/default/files/inline/images/cabify_logo_nuevo_2.png"
                ) to null,
            )
        )

    }
}