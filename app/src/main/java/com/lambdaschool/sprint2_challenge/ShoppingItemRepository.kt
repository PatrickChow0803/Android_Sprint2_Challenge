package com.lambdaschool.sprint2_challenge

class ShoppingItemRepository {
    companion object{
        val shoppingItemList = mutableListOf<ShoppingItem>()
        fun createShoppingItemList(){
            for(index in 0 until ITEM_NAMES_RAW.size){
                shoppingItemList.add(ShoppingItem(ITEM_NAMES_RAW[index], ICON_IDS[index], index, false))
            }
        }
    }
}