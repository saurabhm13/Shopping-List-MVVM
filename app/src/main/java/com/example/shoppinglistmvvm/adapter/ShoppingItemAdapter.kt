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

class ShoppingItemAdapter(
    var item: List<ShoppingItems>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_items, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = item[position]

        holder.name.text = curShoppingItem.name
        holder.amount.text = curShoppingItem.amount.toString()

        holder.delete.setOnClickListener{
            viewModel.delete(curShoppingItem)
        }

        holder.add.setOnClickListener{
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }

        holder.minus.setOnClickListener{
            if (curShoppingItem.amount > 0){
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.tv_name)
        val amount: TextView = itemView.findViewById(R.id.tv_amount)

        val delete: ImageView = itemView.findViewById(R.id.iv_delete)
        val add: ImageView = itemView.findViewById(R.id.iv_add)
        val minus: ImageView = itemView.findViewById(R.id.iv_minus)
    }
}