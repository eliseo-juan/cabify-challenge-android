package dev.eliseo.cabify.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.eliseo.cabify.data.datasource.DBCartDatasource
import dev.eliseo.cabify.data.datasource.NetworkDiscountDatasource
import dev.eliseo.cabify.data.datasource.NetworkProductDatasource
import dev.eliseo.cabify.data.repository.CartRepositoryImpl
import dev.eliseo.cabify.data.repository.DiscountRepositoryImpl
import dev.eliseo.cabify.data.repository.ProductRepositoryImpl
import dev.eliseo.cabify.data.service.ProductAPIService
import dev.eliseo.cabify.domain.repository.CartRepository
import dev.eliseo.cabify.domain.repository.DiscountRepository
import dev.eliseo.cabify.domain.repository.ProductRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideProductAPIService(retrofit: Retrofit): ProductAPIService =
        retrofit.create(ProductAPIService::class.java)


    @Provides
    fun provideCartDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                appContext.preferencesDataStoreFile("cart")
            }
        )
    }

    @Provides
    fun provideCartDatasource(
        dataStore: DataStore<Preferences>,
        gson: Gson
    ): DBCartDatasource {
        return DBCartDatasource(dataStore, gson)
    }

    @Provides
    fun provideProductDatasource(
        productAPIService: ProductAPIService,
    ): NetworkProductDatasource = NetworkProductDatasource(productAPIService)


    @Provides
    fun provideDiscountDatasource(): NetworkDiscountDatasource =
        NetworkDiscountDatasource()

    @Provides
    @Singleton
    fun provideProductRepository(
        productDataSource: NetworkProductDatasource
    ): ProductRepository {
        return ProductRepositoryImpl(productDataSource)
    }

    @Provides
    @Singleton
    fun provideDiscountRepository(
        discountDatasource: NetworkDiscountDatasource
    ): DiscountRepository {
        return DiscountRepositoryImpl(discountDatasource)
    }

    @Provides
    @Singleton
    fun provideCartRepository(
        cartDatasource: DBCartDatasource
    ): CartRepository {
        return CartRepositoryImpl(cartDatasource)
    }
}