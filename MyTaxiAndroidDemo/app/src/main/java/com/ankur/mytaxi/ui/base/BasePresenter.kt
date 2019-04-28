package com.ankur.mytaxi.ui.base

abstract class BasePresenter<V : IBaseView> {

    var mView: V? = null

    fun bindView(view: V) {
        this.mView = view
    }


    fun unbind() {
        this.mView = null
    }

}