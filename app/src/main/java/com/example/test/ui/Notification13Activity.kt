package com.example.test.ui

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
import com.example.test.R
import com.example.test.base.BaseActivity
import com.example.test.databinding.ActivityNotification13Binding
import com.google.android.material.snackbar.Snackbar

class Notification13Activity() : BaseActivity<ActivityNotification13Binding>(R.layout.activity_notification_13) {

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



    companion object {
        const val CHANNEL_ID = "dummy_channel"
    }


    fun observeData() {

    }

    /**
     * Refresh UI elements.
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun refreshUI() {
        viewDataBinding.textNotificationEnabled.text =
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

    override fun onBindView() {
        viewDataBinding.apply {
            //        val binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
            btnHiltTestBtn.setOnClickListener {
                startActivity(Intent(this@Notification13Activity, HiltActivity::class.java))
            }
            ktorBtn.setOnClickListener {
                startActivity(Intent(this@Notification13Activity, KtorActivity::class.java))
            }

            // Sets up notification channel.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel()

                // Sets up button.
                buttonShowNotification.setOnClickListener {
                    if (ContextCompat.checkSelfPermission(this@Notification13Activity, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                        showDummyNotification()
                    } else {
                        requestPermissionLauncher.launch(POST_NOTIFICATIONS)
                    }
                }
            }


            // Refresh UI.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { refreshUI() }
        }
    }

}