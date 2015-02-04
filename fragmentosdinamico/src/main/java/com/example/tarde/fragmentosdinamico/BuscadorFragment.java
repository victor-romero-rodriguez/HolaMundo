package com.example.tarde.fragmentosdinamico;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tarde on 04/02/2015.
 */
public class BuscadorFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // El false es para que no se vincule al contenedor (Para que no se vuelva a crear el contenedor)
        return inflater.inflate(R.layout.fragment_buscador, container, false);
    }
}
