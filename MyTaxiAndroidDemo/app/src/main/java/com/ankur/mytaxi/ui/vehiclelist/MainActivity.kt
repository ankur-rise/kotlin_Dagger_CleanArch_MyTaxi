package com.ankur.mytaxi.ui.vehiclelist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.ankur.mytaxi.R
import com.ankur.mytaxi.di.injector.Injector
import com.ankur.mytaxi.ui.adapter.VehicleRecyclerAdapter
import com.ankur.mytaxi.ui.map.MapActivity
import com.ankur.mytaxi.ui.map.PARAM_VEHICLE_LIST
import com.ankur.mytaxi.viewmodel.PoiList
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IVehicleContract.VehicleListView, VehicleRecyclerAdapter.ItemClickListener {


    @BindView(R.id.rl_vehicle)
    lateinit var rv: RecyclerView
    @BindView(R.id.pb)
    lateinit var pb: ProgressBar
    @Inject
    lateinit var mPresenter: MainPresenter
    @Inject
    lateinit var mAdapter: VehicleRecyclerAdapter
    @Inject
    lateinit var mList: ArrayList<PoiList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        ButterKnife.setDebug(true)
        val component = Injector.instance.getActivityComponent(this, this)
        component?.inject(this)
        mPresenter.bindView(this)
        mPresenter.getVehicle()

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = mAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unbind()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showVehicle(poiList: List<PoiList>) {
        mList.addAll(poiList)
        mAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        pb.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb.visibility = View.GONE
    }

    override fun onItemClick(position: Int) {
        intent = Intent(this, MapActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable(PARAM_VEHICLE_LIST, mList)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}
