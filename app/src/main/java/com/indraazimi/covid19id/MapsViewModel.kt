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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indraazimi.covid19id.model.Provinsi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapsViewModel : ViewModel() {

    private val data = MutableLiveData<List<Provinsi>>()

    fun requestData() = viewModelScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.IO) {
            try {
                val result = Covid19Api.service.getProvinsi()
                data.postValue(result.data)
            } catch (e: Exception) {
                Log.d("MAPS", e.message.toString())
            }
        }
    }

    fun getData(): LiveData<List<Provinsi>> = data
}