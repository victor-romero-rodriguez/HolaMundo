package com.example.tarde.serviciolocal;

import android.os.Binder;

/**
 * Created by tarde on 05/02/2015.
 */
public class MainBinder extends Binder {

    private MainService service;

    public MainBinder(MainService service) {
        this.service = service;
    }

    public MainService getService(){
        return  service;
    }

}
