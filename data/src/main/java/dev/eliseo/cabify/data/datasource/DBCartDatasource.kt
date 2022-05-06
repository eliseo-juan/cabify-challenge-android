package dev.eliseo.cabify.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DBCartDatasource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val cartAdapter: JsonAdapter<List<String>>
) {

    companion object {
        val KEY_CART = stringPreferencesKey("example_counter")
    }

    suspend fun getCart(): Flow<List<String>> {
        return dataStore.data.map {
            it[KEY_CART]?.let { json -> cartAdapter.fromJson(json) } ?: emptyList()
        }
    }

    suspend fun addProduct(productCode: String) {
        dataStore.edit {
            val currentList =
                it[KEY_CART]?.let { json -> cartAdapter.fromJson(json) } ?: emptyList()
            it[KEY_CART] =
                cartAdapter.toJson(currentList.toMutableList().apply { add(productCode) })
        }
    }

    suspend fun removeProduct(productCode: String) {
        dataStore.edit {
            val currentList =
                it[KEY_CART]?.let { json -> cartAdapter.fromJson(json) } ?: emptyList()
            it[KEY_CART] =
                cartAdapter.toJson(currentList.toMutableList().apply { remove(productCode) })
        }
    }

}