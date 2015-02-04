package com.example.tarde.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements AdapterView.OnClickListener {

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(this);

        builder.setTitle("Advertencia")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Se acepto", Toast.LENGTH_LONG).show();
                        // Para terminar dialogo de forma correcta
                        dialog.dismiss();
                        // O cancel
                        //dialog.cancel();
                    }
                });

        View actividad = findViewById(R.id.btActividad);
        actividad.setOnClickListener(this);

        View fragmento = findViewById(R.id.btFragmento);
        fragmento.setOnClickListener(this);

        View personalizado = findViewById(R.id.btPersonalizado);
        personalizado.setOnClickListener(this);

        View multiple = findViewById(R.id.btMultiple);
        multiple.setOnClickListener(this);
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        return builder.create();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btActividad:
                showDialog(1);
                break;
            case R.id.btFragmento:
                AdvertenciaDialogFragment dialogFragment = new AdvertenciaDialogFragment();
                dialogFragment.show(getFragmentManager(), "Dialog");
                break;
            case R.id.btPersonalizado:
                PersonalizadoDialogFragment dialogPersonalizado = new PersonalizadoDialogFragment();
                dialogPersonalizado.show(getFragmentManager(), "Dialog");
                break;
            case R.id.btMultiple:
                SeleccionMultipleDialogFragment dialogMultiple = new SeleccionMultipleDialogFragment();
                dialogMultiple.show(getFragmentManager(), "Dialog");
                break;
        }
    }
}
