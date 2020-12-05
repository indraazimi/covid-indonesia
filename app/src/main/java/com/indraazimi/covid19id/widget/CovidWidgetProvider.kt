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
import com.indraazimi.covid19id.MainActivity
import com.indraazimi.covid19id.R

class CovidWidgetProvider : AppWidgetProvider() {

    companion object {
        private fun updateAppWidget(context: Context, manager: AppWidgetManager, id: Int) {
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            val views = RemoteViews(context.packageName, R.layout.widget_main)
            views.setOnClickPendingIntent(R.id.dataTextView, pendingIntent)
            manager.updateAppWidget(id, views)
        }
    }

    override fun onUpdate(context: Context?, manager: AppWidgetManager?, ids: IntArray?) {
        if (context == null || manager == null || ids == null) return

        // Pengguna dapat menambahkan lebih dari 1 widget di home screen
        // Jadi kita harus melakukan update ke semua widget yang ada.
        for (id in ids) {
            updateAppWidget(context, manager, id)
        }
    }
}
