package com.ankur.mytaxi.ui.base;

public abstract class Base<V extends IBaseView> {

    V mView;

    public void bindView(V view) {
        this.mView = view;
    }


    public void unbind() {
        this.mView = null;
    }




}
