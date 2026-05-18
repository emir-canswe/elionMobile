package com.elion.assistant.service.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.elion.assistant.data.local.preferences.AppPreferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var prefs: AppPreferences

    @Inject
    lateinit var notificationHelper: NotificationHelper

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            notificationHelper.createChannels()
            CoroutineScope(Dispatchers.IO).launch {
                val mh = prefs.morningHour.first()
                val mm = prefs.morningMinute.first()
                val eh = prefs.eveningHour.first()
                val em = prefs.eveningMinute.first()
                WorkScheduler.scheduleDailyNotifications(context, mh, mm, eh, em)
            }
        }
    }
}
