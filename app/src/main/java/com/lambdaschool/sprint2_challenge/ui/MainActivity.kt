package com.lambdaschool.sprint2_challenge.ui

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.ShoppingItemRepository
import com.lambdaschool.sprint2_challenge.adapter.ShoppingListAdapter
import kotlinx.android.synthetic.main.activity_main.*


/*
    steps
    1. Create a class that takes in the two arrays
    2. Create a Model class for the data
    3. Make the card view
    4. Make the adapter
    5. Link up the RecyclerView
    6. Create the set on click listener
    7. Create the intent to share
    8. Create the notification
 */
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Fill up the repository using the data given
        ShoppingItemRepository.createShoppingItemList()

        //Lines 39 - 44 Link up the recyclerview
        rv_shopping_list.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ShoppingListAdapter(ShoppingItemRepository.shoppingItemList)
        }

        btn_share.setOnClickListener {

            var message = "Buy these things: "

            //Search the repository and look through to see what has been selected.
            //Selected items are placed into the message.
            for(shopping in ShoppingItemRepository.shoppingItemList){
                if(shopping.isSelected)
                    message += "${shopping.name}, "
            }

            //Lines 57 - 64 to build an intent to share text
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, message)
            shareIntent.type = "text/plain"

            //Brings up the list of apps to choose from
            startActivity(Intent.createChooser(shareIntent, "Who would you like to share your shopping list to? "))

            //Lines 66 - 94 are for notifications

            var notificationManager: NotificationManager
            var notificationChannel: NotificationChannel
            var builder: Notification.Builder
            val channelId = packageName
            val description = "Test Notification"

            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            //Notification channel is only for Android systems above oreo
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this, channelId)
                        .setContentTitle("Shopping List Confirmation")
                        .setContentText(message)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
            }

            else{
                builder = Notification.Builder(this)
                        .setContentTitle("Shopping List Confirmation")
                        .setContentText(message)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
            }
            notificationManager.notify(0, builder.build())
        }
    }
}
