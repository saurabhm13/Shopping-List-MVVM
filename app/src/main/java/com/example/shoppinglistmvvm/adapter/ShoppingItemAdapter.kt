package com.example.shoppinglistmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistmvvm.R
import com.example.shoppinglistmvvm.data.db.entities.ShoppingItems
import com.example.shoppinglistmvvm.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_items.view.*

class ShoppingItemAdapter(
    var items: List<ShoppingItems>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_items, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]

        holder.itemView.tv_name.text = curShoppingItem.name
        holder.itemView.tv_amount.text = "${curShoppingItem.amount}"

        holder.itemView.iv_delete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        holder.itemView.iv_add.setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }

        holder.itemView.iv_minus.setOnClickListener {
            if(curShoppingItem.amount > 0) {
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }
    }

    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}