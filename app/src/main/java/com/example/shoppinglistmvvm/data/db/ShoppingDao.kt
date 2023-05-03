package com.example.shoppinglistmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppinglistmvvm.data.db.entities.ShoppingItems

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItems)

    @Delete
    suspend fun delete(item: ShoppingItems)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingData(): LiveData<List<ShoppingItems>>

}