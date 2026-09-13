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
            
            // Update widget
            val updateIntent = Intent(context, com.example.sensei.widget.SenseiWidgetProvider::class.java).apply {
                action = android.appwidget.AppWidgetManager.ACTION_APPWIDGET_UPDATE
            }
            val ids = android.appwidget.AppWidgetManager.getInstance(context).getAppWidgetIds(
                android.content.ComponentName(context, com.example.sensei.widget.SenseiWidgetProvider::class.java)
            )
            updateIntent.putExtra(android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
            context.sendBroadcast(updateIntent)
            
            // Notify list view to refresh data
            android.appwidget.AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(ids, com.example.sensei.R.id.widget_list_view)
        }
    }

    companion object {
        const val ACTION_CLEAR_STATUS = "com.example.sensei.ACTION_CLEAR_STATUS"
    }
}
