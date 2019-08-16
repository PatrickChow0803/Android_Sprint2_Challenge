package com.lambdaschool.sprint2_challenge

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
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



        btn_share.setOnClickListener {

            var message = "Buy these things: "

            //Lines 35 - 46 to build an intent to share text
            for(shopping in ShoppingItemRepository.shoppingItemList){
                if(shopping.isSelected)
                    message += "${shopping.name} "
            }

            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, message)
            shareIntent.type = "text/plain"

            startActivity(Intent.createChooser(shareIntent, "Who would you like to share your shopping list to? "))

            //Lines 48 - 76 are for notifications

            var notificationManager: NotificationManager
            var notificationChannel: NotificationChannel
            var builder: Notification.Builder
            val channelId = packageName
            val description = "Test Notification"
            
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

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
