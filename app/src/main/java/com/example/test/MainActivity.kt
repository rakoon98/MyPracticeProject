package com.example.test

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.test.databinding.ActivityMainBinding
import com.example.test.databinding.ActivityMainBindingImpl
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding

    private val notificationManager: NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private val requestPermissionLauncher: ActivityResultLauncher<String>
    // Sets up permissions request launcher.
     = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        refreshUI()
        if (it) {
            showDummyNotification()
        } else {
            Snackbar.make(
                findViewById<View>(android.R.id.content).rootView,
                "Please grant Notification permission from App Settings",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView<ActivityMainBinding?>(this, R.layout.activity_main).apply {
            btnHiltTestBtn.setOnClickListener {
                startActivity(
                    Intent(this@MainActivity, HiltActivity::class.java)
                )
            }
        }
//        val binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        // Sets up notification channel.
        createNotificationChannel()

        // Sets up button.
        findViewById<Button>(R.id.button_show_notification).setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    POST_NOTIFICATIONS,
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                showDummyNotification()
            } else {
                requestPermissionLauncher.launch(POST_NOTIFICATIONS)
            }
        }

        // Refresh UI.
        refreshUI()
    }



    /**
     * Refresh UI elements.
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun refreshUI() {
        findViewById<TextView>(R.id.text_notification_enabled).text =
            if (notificationManager.areNotificationsEnabled()) "TRUE" else "FALSE"
    }


    /**
     * Creates Notification Channel (required for API level >= 26) before sending any notification.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Important Notification Channel",
            NotificationManager.IMPORTANCE_HIGH,
        ).apply {
            description = "This notification contains important announcement, etc."
        }
        notificationManager.createNotificationChannel(channel)
    }

    /**
     * Shows a notification to user.
     *
     * The notification won't appear if the user doesn't grant notification permission first.
     */
    private fun showDummyNotification() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Congratulations! 🎉🎉🎉")
            .setContentText("You have post a notification to Android 13!!!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }
    }

    companion object {
        const val CHANNEL_ID = "dummy_channel"
    }

}