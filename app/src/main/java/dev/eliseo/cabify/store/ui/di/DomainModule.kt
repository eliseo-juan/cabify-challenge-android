package dev.eliseo.cabify.store.ui.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.eliseo.cabify.domain.repository.CartRepository
import dev.eliseo.cabify.domain.repository.DiscountRepository
import dev.eliseo.cabify.domain.repository.ProductRepository
import dev.eliseo.cabify.domain.usecase.*

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetProductWithDiscountsListUseCase(
        productRepository: ProductRepository,
        discountRepository: DiscountRepository
    ): GetProductWithDiscountsListUseCase =
        GetProductWithDiscountsListUseCaseImpl(productRepository, discountRepository)

    @Provides
    fun provideGetProductUseCase(productRepository: ProductRepository): GetProductUseCase =
        GetProductUseCaseImpl(productRepository)

    @Provides
    fun provideGetCartPriceUseCase(): GetCartPriceUseCase =
        GetCartPriceUseCaseImpl()

    @Provides
    fun provideGetCartListUseCase(
        productRepository: ProductRepository,
        discountRepository: DiscountRepository,
        cartRepository: CartRepository,
    ): GetCartListUseCase =
        GetCartListUseCaseImpl(
            productRepository = productRepository,
            discountRepository = discountRepository,
            cartRepository = cartRepository
        )

    @Provides
    fun provideGetCartListItemUseCase(
        productRepository: ProductRepository,
        discountRepository: DiscountRepository,
        cartRepository: CartRepository,
    ): GetCartListItemUseCase =
        GetCartListItemUseCaseImpl(
            productRepository = productRepository,
            discountRepository = discountRepository,
            cartRepository = cartRepository
        )

    @Provides
    fun provideAddProductToCartUseCase(
        cartRepository: CartRepository,
    ): AddProductToCartUseCase =
        AddProductToCartUseCaseImpl(
            cartRepository = cartRepository
        )

    @Provides
    fun provideRemoveProductToCartUseCase(
        cartRepository: CartRepository,
    ): RemoveProductToCartUseCase =
        RemoveProductToCartUseCaseImpl(
            cartRepository = cartRepository
        )
}