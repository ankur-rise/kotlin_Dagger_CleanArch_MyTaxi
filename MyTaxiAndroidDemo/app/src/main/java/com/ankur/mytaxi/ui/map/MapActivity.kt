package com.ankur.mytaxi.ui.map

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ankur.mytaxi.R

class MapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val bundle = intent.extras
        setMapFragment(bundle)
    }

    private fun setMapFragment(bundle: Bundle) {
        val fragment:VehicleMapFragment = getMapFragment(bundle)
        supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()
    }

    private fun getMapFragment(bundle: Bundle): VehicleMapFragment {
//       val fragment:MapFragment = MapFragment.newInstance(bundle)
        val fragment = VehicleMapFragment()
        fragment.arguments = bundle
        return fragment
    }
}
