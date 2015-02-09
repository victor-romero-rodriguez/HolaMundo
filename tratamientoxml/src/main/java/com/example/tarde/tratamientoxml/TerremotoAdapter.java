package com.example.tarde.tratamientoxml;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tarde on 03/02/2015.
 */
public class TerremotoAdapter extends BaseAdapter {


    private final Activity contexto;
    private final int layout;
    private final List<Terremoto> listado;

    public TerremotoAdapter(Activity contexto, int resLayout, List<Terremoto> listado) {
        this.contexto = contexto;
        this.layout = resLayout;
        this.listado = listado;
    }

    @Override
    public int getCount() {
        return listado.size();
    }

    @Override
    public Object getItem(int position) {
        return listado.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtenemos el Item
        Terremoto terremoto = (Terremoto) getItem(position);

        TerremotoViewHelper peliculaViewHelper;

        // Ahorra la creación de objetos de tipo View, para repintados del AdpaterView
        if (convertView != null) {
            // No necesitamos inflar
            peliculaViewHelper = (TerremotoViewHelper) convertView.getTag();

        } else {
            // Inflamos un objeto view para poder pintar los datos del Item
            convertView = contexto.getLayoutInflater().inflate(layout, null);

            // De este modo reaprobechamos los objetos pintados, sin necesidad de volver a pintarlos

            // Obtenemos los elementos del View
            peliculaViewHelper = new TerremotoViewHelper();
            // SE hace así para que no se sobrecarge el método finViewById que es muy costoso
            peliculaViewHelper.titulo = (TextView) convertView.findViewById(R.id.titulo);
            peliculaViewHelper.magnitud = (TextView) convertView.findViewById(R.id.magnitud);
            peliculaViewHelper.link = (TextView) convertView.findViewById(R.id.link);
            peliculaViewHelper.id = (TextView) convertView.findViewById(R.id.id);
            peliculaViewHelper.fecha = (TextView) convertView.findViewById(R.id.fecha);
            peliculaViewHelper.latitud = (TextView) convertView.findViewById(R.id.latitud);
            peliculaViewHelper.longitud = (TextView) convertView.findViewById(R.id.longitud);

            convertView.setTag(peliculaViewHelper);
        }

        peliculaViewHelper.id.setText(terremoto.getId());
        peliculaViewHelper.titulo.setText(terremoto.getTitulo());
        peliculaViewHelper.link.setText(terremoto.getLink());
        peliculaViewHelper.magnitud.setText(String.valueOf(terremoto.getMagnitud()));
        peliculaViewHelper.fecha.setText(String.valueOf(terremoto.getFecha()));
        peliculaViewHelper.longitud.setText(String.valueOf(terremoto.getLongitud()));
        peliculaViewHelper.latitud.setText(String.valueOf(terremoto.getLatitud()));

        // Esto se hace por cada uno de los elementos de la lista, es decir hay un view distinto.
        // Por eso se vuelve a obtener la referencia de los objetos, incluido cuando se vuelve a
        // repintar la lista

        // Para no sobrecargar el procesador debremos realizar varias mejoras
        return convertView;
    }

    // Clase de apoyo, para mantener las referencias a los elementos View del layout, para no tener
    // que
    class TerremotoViewHelper {
        // Es un almacen de referencias
        TextView titulo;
        TextView magnitud;
        TextView link;
        TextView fecha;
        TextView id;
        TextView longitud;
        TextView latitud;
    }


}
