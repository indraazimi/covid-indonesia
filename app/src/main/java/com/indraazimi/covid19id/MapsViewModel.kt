/*
 * Copyright (c) 2021 Indra Azimi. All rights reserved.
 *
 * Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
 * Dilarang melakukan penggandaan dan atau komersialisasi,
 * sebagian atau seluruh bagian, baik cetak maupun elektronik
 * terhadap project ini tanpa izin pemilik hak cipta.
 */

package com.indraazimi.covid19id

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapsViewModel : ViewModel() {

    fun requestData() = viewModelScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.IO) {
            try {
                val result = Covid19Api.service.getProvinsi()
                Log.d("MAPS", "Jumlah data: " + result.data.size)
            } catch (e: Exception) {
                Log.d("MAPS", e.message.toString())
            }
        }
    }
}