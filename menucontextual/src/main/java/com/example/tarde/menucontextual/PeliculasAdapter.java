package com.example.tarde.menucontextual;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tarde on 03/02/2015.
 */
public class PeliculasAdapter extends BaseAdapter {


    private final Activity contexto;
    private final int layout;
    private final List<Pelicula> listado;

    public PeliculasAdapter(Activity contexto, int resLayout, List<Pelicula> listado) {
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

    public void addPelicula(Pelicula pelicula) {
        if (pelicula != null) {
            listado.add(pelicula);
            // Para que se repinte la lista
            notifyDataSetChanged();
        }
    }

    public void removePelicula(int position) {
        if (listado.get(position) != null) {
            listado.remove(position);
            notifyDataSetChanged();
        }
    }

    public void editPelicula(int position, Pelicula newPelicula) {
        Pelicula oldPelicula = listado.get(position);
        oldPelicula.setTitulo(newPelicula.getTitulo());
        oldPelicula.setAnio(newPelicula.getAnio());
        oldPelicula.setActores(newPelicula.getActores());
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtenemos el Item
        Pelicula pelicula = (Pelicula) getItem(position);

        PeliculaViewHelper peliculaViewHelper;

        // Ahorra la creación de objetos de tipo View, para repintados del AdpaterView
        if (convertView != null) {
            // No necesitamos inflar
            peliculaViewHelper = (PeliculaViewHelper) convertView.getTag();

        } else {
            // Inflamos un objeto view para poder pintar los datos del Item
            convertView = contexto.getLayoutInflater().inflate(layout, null);

            // De este modo reaprobechamos los objetos pintados, sin necesidad de volver a pintarlos

            // Obtenemos los elementos del View
            peliculaViewHelper = new PeliculaViewHelper();
            // SE hace así para que no se sobrecarge el método finViewById que es muy costoso
            peliculaViewHelper.titulo = (TextView) convertView.findViewById(R.id.titulo);
            peliculaViewHelper.anio = (TextView) convertView.findViewById(R.id.anio);
            peliculaViewHelper.actores = (TextView) convertView.findViewById(R.id.actores);

            convertView.setTag(peliculaViewHelper);
        }

        peliculaViewHelper.titulo.setText(pelicula.getTitulo());
        peliculaViewHelper.anio.setText(String.valueOf(pelicula.getAnio()));
        peliculaViewHelper.actores.setText(pelicula.getActores().toString());

        // Esto se hace por cada uno de los elementos de la lista, es decir hay un view distinto.
        // Por eso se vuelve a obtener la referencia de los objetos, incluido cuando se vuelve a
        // repintar la lista

        // Para no sobrecargar el procesador debremos realizar varias mejoras
        return convertView;
    }

    // Clase de apoyo, para mantener las referencias a los elementos View del layout, para no tener
    // que
    class PeliculaViewHelper {
        // Es un almacen de referencias
        TextView titulo;
        TextView anio;
        TextView actores;
    }


}
