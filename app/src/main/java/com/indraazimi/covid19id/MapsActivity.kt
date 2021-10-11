/*
 * Copyright (c) 2021 Indra Azimi. All rights reserved.
 *
 * Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
 * Dilarang melakukan penggandaan dan atau komersialisasi,
 * sebagian atau seluruh bagian, baik cetak maupun elektronik
 * terhadap project ini tanpa izin pemilik hak cipta.
 */

package com.indraazimi.covid19id

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.indraazimi.covid19id.model.Provinsi

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val viewModel: MapsViewModel by lazy {
        ViewModelProvider(this).get(MapsViewModel::class.java)
    }

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModel.getData().observe(this, { data ->
            data.forEach { createMarker(it) }
        })
    }

    private fun createMarker(prov: Provinsi) {
        val lokasi = LatLng(prov.lokasi.lat, prov.lokasi.lon)
        val snippet = getString(R.string.jumlah_dirawat, prov.dirawat)
        map.addMarker(MarkerOptions()
            .position(lokasi)
            .title(prov.key)
            .snippet(snippet))
    }

    override fun onMapReady(gMap: GoogleMap) {
        map = gMap
        viewModel.requestData()

        val lokasi = LatLng(-6.920432082789247, 107.60370834146391)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasi, 7f))
    }
}