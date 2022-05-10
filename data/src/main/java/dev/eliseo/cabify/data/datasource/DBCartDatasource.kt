package dev.eliseo.cabify.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.reflect.Type
import javax.inject.Inject

class DBCartDatasource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val gson: Gson
) {

    companion object {
        val KEY_CART = stringPreferencesKey("example_counter")
    }

    var typeAdapter: Type = object : TypeToken<List<String>>() {}.type

    suspend fun getCart(): Flow<List<String>> {
        return dataStore.data.map {
            it[KEY_CART]?.let { json -> gson.fromJson(json, typeAdapter) } ?: emptyList()
        }
    }

    suspend fun addProduct(productCode: String) {
        dataStore.edit {
            val currentList: List<String> =
                it[KEY_CART]?.let { json -> gson.fromJson(json, typeAdapter) } ?: emptyList()
            it[KEY_CART] = gson.toJson(currentList.toMutableList().apply { add(productCode) })
        }
    }

    suspend fun removeProduct(productCode: String) {
        dataStore.edit {
            val currentList: List<String> =
                it[KEY_CART]?.let { json -> gson.fromJson(json, typeAdapter) } ?: emptyList()
            it[KEY_CART] = gson.toJson(currentList.toMutableList().apply { remove(productCode) })
        }
    }

}