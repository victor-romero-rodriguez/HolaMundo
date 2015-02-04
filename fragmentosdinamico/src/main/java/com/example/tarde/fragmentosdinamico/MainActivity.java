package com.example.tarde.fragmentosdinamico;

import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction tx = getFragmentManager().beginTransaction();
        // Para modificar los componentes que existen en la transacción

        // Añadir el fragmento
        tx.add(R.id.contenedor, new BuscadorFragment());

        // Consolidar el cambio
        tx.commit();
    }



}
