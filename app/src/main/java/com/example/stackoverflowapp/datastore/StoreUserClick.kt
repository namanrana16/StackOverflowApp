package com.example.stackoverflowapp.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserClick(private val context: Context) {

    companion object{
        private val Context.dataStore by androidx.datastore.preferences.preferencesDataStore(name = "user_click")
        val USER_CLICK_COUNT= intPreferencesKey("user_click_count")
    }


    val getCount: Flow<Int?> = context.dataStore.data.map { preferences ->
        preferences[USER_CLICK_COUNT] ?: 0
    }

    suspend fun saveCount(count:Int){
        context.dataStore.edit { preferences ->
            preferences[USER_CLICK_COUNT]=count
        }
    }
}