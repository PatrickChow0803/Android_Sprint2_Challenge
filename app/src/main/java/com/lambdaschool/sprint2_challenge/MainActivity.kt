package com.lambdaschool.sprint2_challenge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ShoppingItemRepository.createShoppingItemList()

        rv_shopping_list.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ShoppingListAdapter(ShoppingItemRepository.shoppingItemList)
        }
    }
}
