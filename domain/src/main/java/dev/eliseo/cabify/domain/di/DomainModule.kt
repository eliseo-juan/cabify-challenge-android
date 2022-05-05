package dev.eliseo.cabify.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.eliseo.cabify.domain.usecase.GetProductWithDiscountsListUseCase
import dev.eliseo.cabify.domain.usecase.GetProductWithDiscountsListUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetProductWithDiscountsListUseCase(): GetProductWithDiscountsListUseCase =
        GetProductWithDiscountsListUseCaseImpl()
}