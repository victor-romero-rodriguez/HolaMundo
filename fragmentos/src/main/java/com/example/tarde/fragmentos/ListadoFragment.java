package com.example.tarde.fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tarde on 02/02/2015.
 */
public class ListadoFragment extends Fragment {


    private ListView listado = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        // SE ejecuta antes que el "onActivityCreated"
        // Indicar el view que conforman el layout (se hace el inflado)

        // Aquí no es recomendable obtener la refencia a los objetos para los que queremos manejar sus ahndler

        return inflater.inflate(R.layout.fragment_listado, container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // El formulario ya está inflado
        // Esto corresponde a lo que hariamos en el onCreate de un Activity

        // De momento crearemos un array
        List<CorreoElectronico> lista =Arrays.asList(
                new CorreoElectronico("Victor", "Prioritario", "Este correo es importante"),
                new CorreoElectronico("Pepe", "Nuevo curso", "Mañana comienza el nuevo curso"),
                new CorreoElectronico("Juan", "Examen", "El Jueves hay un examen"),
                new CorreoElectronico("Miguel", "Depedida", "El Viernes me marcho")
        );

        listado = (ListView)getActivity().findViewById(R.id.lvCorreos);

        // No hay un método set para definir los items del listado
        // Esa misión es del adapter

        ListAdapter adaptadorCorreos = new ArrayAdapter<CorreoElectronico>(
                getActivity(), android.R.layout.simple_list_item_1,lista);

        // El layout que le pasamos puede ser cualquiera que yo defina, siempre y cuando le psasmos un método set con un id text1

        listado.setAdapter(adaptadorCorreos);

        //listado.setOnItemClickListener((MainActivity).getActivity);

        //Tenemos 2 opciones
        // (MainActivity).getActivity
        // La correcta, inyección de dependencias con Set
        // Creamos método nuevo "setOnItemClickListener(...)"
    }



    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        //((ListView)getActivity().findViewById(R.id.lvCorreos)).setOnItemClickListener(listener);
        listado.setOnItemClickListener(listener);
    }

}
