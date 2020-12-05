/*
 * Copyright (c) 2020 Indra Azimi. All rights reserved.
 *
 * Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
 * Dilarang melakukan penggandaan dan atau komersialisasi,
 * sebagian atau seluruh bagian, baik cetak maupun elektronik
 * terhadap project ini tanpa izin pemilik hak cipta.
 */

package com.indraazimi.covid19id.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.preference.PreferenceManager
import com.indraazimi.covid19id.MainActivity
import com.indraazimi.covid19id.R
import java.text.SimpleDateFormat
import java.util.*

class CovidWidgetProvider : AppWidgetProvider() {

    companion object {
        fun updateAllWidget(context: Context, manager: AppWidgetManager, ids: IntArray) {
            for (id in ids) {
                updateAppWidget(context, manager, id)
            }
        }

        private fun updateAppWidget(context: Context, manager: AppWidgetManager, id: Int) {
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            val views = RemoteViews(context.packageName, R.layout.widget_main)
            views.setOnClickPendingIntent(R.id.dataPanel, pendingIntent)

            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val date = prefs.getLong(PrefUtils.KEY_DATE, -1L)
            val data = prefs.getInt(PrefUtils.KEY_DATA, -1)
            updateUI(context, views, date, data)

            manager.updateAppWidget(id, views)
        }

        private fun updateUI(context: Context, views: RemoteViews, date: Long, data: Int) {
            if (date == -1L || data == -1) {
                views.setTextViewText(R.id.dateTextView, context.getString(R.string.belum_ada_data))
                views.setTextViewText(R.id.dataTextView, context.getString(R.string.sad_emoji))
                views.setTextViewText(R.id.descTextView, context.getString(R.string.klik_refresh))
            }
            else {
                val formatter = SimpleDateFormat("dd MMMM yyyy", Locale("ID", "id"))
                views.setTextViewText(R.id.dateTextView, formatter.format(date))
                views.setTextViewText(R.id.dataTextView, data.toString())
                views.setTextViewText(R.id.descTextView, context.getString(R.string.kasus_positif))
            }
        }
    }

    override fun onUpdate(context: Context?, manager: AppWidgetManager?, ids: IntArray?) {
        if (context == null || manager == null || ids == null) return

        // Pengguna dapat menambahkan lebih dari 1 widget di home screen
        // Jadi kita harus melakukan update ke semua widget yang ada.
        updateAllWidget(context, manager, ids)
    }
}
