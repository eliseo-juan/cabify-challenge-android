package dev.eliseo.cabify.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.eliseo.cabify.data.datasource.DBCartDatasource
import dev.eliseo.cabify.data.datasource.NetworkProductDatasource
import dev.eliseo.cabify.data.service.ProductAPIService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideCartDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                appContext.preferencesDataStoreFile("cart")
            }
        )
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    fun provideCartDatasource(
        dataStore: DataStore<Preferences>,
        moshi: Moshi
    ): CartDatasource {
        val listMyData: Type = Types.newParameterizedType(
            MutableList::class.java,
            String::class.java
        )
        return DBCartDatasource(dataStore, moshi.adapter(listMyData))
    }

    @Provides
    fun provideProductDatasource(
        productAPIService: ProductAPIService,
    ): ProductDatasource = NetworkProductDatasource(productAPIService)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/palcalde/6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun provideProductAPIService(retrofit: Retrofit): ProductAPIService =
        retrofit.create(ProductAPIService::class.java)

}