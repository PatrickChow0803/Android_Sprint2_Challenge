package com.lambdaschool.sprint2_challenge

import com.lambdaschool.sprint2_challenge.model.ShoppingItem

//A repository to keep track of the shopping list. Uses "ShoppingItemConstants.kt" for the data
class ShoppingItemRepository {
    companion object{
        val shoppingItemList = mutableListOf<ShoppingItem>()

        //Creates the mutableListOf shopping items
        fun createShoppingItemList(){
            for(index in 0 until ITEM_NAMES_RAW.size){
                shoppingItemList.add(ShoppingItem(ITEM_NAMES_RAW[index], ICON_IDS[index], index, false))
            }
        }
    }
}