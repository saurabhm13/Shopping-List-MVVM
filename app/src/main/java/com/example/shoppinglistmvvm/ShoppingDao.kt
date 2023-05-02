package com.example.shoppinglistmvvm

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItems)

    @Delete
    suspend fun delete(item: ShoppingItems)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingData(): LiveData<List<ShoppingItems>>

}