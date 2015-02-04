package com.example.tarde.dialogos;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by tarde on 04/02/2015.
 */
public class PersonalizadoDialogFragment extends DialogFragment {

    private AlertDialog.Builder builder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Para Definir nuestra factoría

        builder = new AlertDialog.Builder(getActivity());

        // Para definir el arbol de componentes
        // No le puedo dar el entero, por problemas de compatibilidad con la versión
        //builder.setView()

        //No necesitamos pasarle un container, porque se va a pintar el dialogo
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_personalizado, null);

        builder.setView(view)
               .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       String sDato1 = ((EditText)((AlertDialog)dialog).findViewById(R.id.etDato1)).getText().toString();
                       String sDato2 = ((EditText)((AlertDialog)dialog).findViewById(R.id.etDato2)).getText().toString();

                       Toast.makeText(getActivity(), "Dato1= " + sDato1+ "; Dato2= " + sDato2, Toast.LENGTH_LONG).show();
                   }
               });

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return builder.create();
    }
}