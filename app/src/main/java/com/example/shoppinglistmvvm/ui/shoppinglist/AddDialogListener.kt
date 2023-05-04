package com.example.shoppinglistmvvm.ui.shoppinglist

import com.example.shoppinglistmvvm.data.db.entities.ShoppingItems

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItems)
}