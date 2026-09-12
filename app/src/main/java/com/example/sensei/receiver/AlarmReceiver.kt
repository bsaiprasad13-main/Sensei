package com.example.sensei.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.sensei.data.StatusRepository
import com.example.sensei.service.StatusService

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ACTION_CLEAR_STATUS) {
            val repository = StatusRepository(context)
            repository.clearStatus()
            StatusService.stop(context)
        }
    }

    companion object {
        const val ACTION_CLEAR_STATUS = "com.example.sensei.ACTION_CLEAR_STATUS"
    }
}
