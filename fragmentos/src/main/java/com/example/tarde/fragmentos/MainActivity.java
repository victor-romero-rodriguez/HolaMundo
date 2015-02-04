package com.example.tarde.fragmentos;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    private ListadoFragment fragmentoListado;

    private DetalleFragment fragmentoDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Sigo con lo mismo, depende del estado del terminal para cargar uno u otro.
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();

        fragmentoListado = (ListadoFragment)fragmentManager.findFragmentById(R.id.fragmentoListado);

        fragmentoDetalle = (DetalleFragment)fragmentManager.findFragmentById(R.id.fragmentoDetalle);


    }

    // Definiemos el registro del listener en el onResume, para evitar un NullPointer, que se produce
    // cuando se registra en el onCreate ya que todaví no existe el atributo de clase listado de
    // fragmentoListado
    @Override
    protected void onResume() {
        super.onResume();

        // Definir el listener para el onClick sobre la lista
        fragmentoListado.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        CorreoElectronico item = (CorreoElectronico)parent.getItemAtPosition(position);

        if(fragmentoDetalle != null){
            // Estamos en una tablet
            fragmentoDetalle.actualizarDetalle(item);
        }else{
            //Estamos en un SmartPhone
            Intent intent = new Intent(this, DetalleActivity.class);
            intent.putExtra(DetalleActivity.KEY_CORREO_ITEM, item);
            startActivity(intent);
        }
    }
}
