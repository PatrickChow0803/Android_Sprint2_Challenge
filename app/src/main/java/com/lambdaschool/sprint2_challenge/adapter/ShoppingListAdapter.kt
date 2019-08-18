package com.lambdaschool.sprint2_challenge.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.model.ShoppingItem
import kotlinx.android.synthetic.main.shopping_list_item.view.*

class ShoppingListAdapter(val shoppingList: MutableList<ShoppingItem>): RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //Obtain the views
        val shoppingImageView: ImageView = view.img_shopping
        val shoppingNameView: TextView = view.tv_shopping
        val shoppingListView: LinearLayout = view.ll_shopping

        //Edits the views to our desires
        fun editModel(shopping: ShoppingItem){
            shoppingImageView.setImageResource(shopping.image)
            shoppingNameView.text = shopping.name
            if (shopping.isSelected) {
                shoppingListView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.secondaryColor))
                shoppingNameView.setTextColor(ContextCompat.getColor(itemView.context, R.color.secondaryTextColor))
            }
            else {
                shoppingListView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.primaryColor))
                shoppingNameView.setTextColor(ContextCompat.getColor(itemView.context, R.color.primaryTextColor))
            }
        }
    }

    //Converts the XML card into a viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.shopping_list_item, parent, false) as View)
    }

    //Returns the size of the shoppingList
    override fun getItemCount(): Int {
        return shoppingList.size
    }

    //Edits the views to our desires
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shopping = shoppingList[position]
        holder.editModel(shopping)
        holder.shoppingListView.setOnClickListener {
            shopping.isSelected = !shopping.isSelected
            notifyItemChanged(position)
        }
    }
}