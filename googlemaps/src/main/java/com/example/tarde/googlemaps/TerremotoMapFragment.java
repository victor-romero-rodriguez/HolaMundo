package com.example.tarde.googlemaps;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by tarde on 12/02/2015.
 */
public class TerremotoMapFragment extends MapFragment {

    public void dibujarTerremoto(Terremoto terremoto){

        // Referencia al mapa, nos la dará el fragmento
        GoogleMap map = getMap();

        // Centrar el mapa en un punto geográfico y zoom
        LatLng position = new LatLng(terremoto.getLatitud(), terremoto.getLongitud());
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(position, 10);
        map.animateCamera(camera);

        // Crear la chincheta
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title(terremoto.getTitulo())
                .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_mylocation))
                .snippet("Magnitud: " + terremoto.getMagnitud())
                .position(position);
        map.addMarker(markerOptions);
    }
}
