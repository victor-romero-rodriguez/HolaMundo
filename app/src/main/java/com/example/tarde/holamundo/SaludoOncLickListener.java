package com.example.tarde.holamundo;

import android.app.Activity;
import android.view.View;

/**
 * Created by tarde on 29/01/2015.
 */
public class SaludoOncLickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {

        // Obtenemos el contexto en que fue creado el objeto, en este caso el main-activity
        Activity context = (Activity)v.getContext();

        // Podría coger referencia a otro Objeto
        //context.findViewById();


    }
}
