package com.example.tarde.asynctask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tarde on 04/02/2015.
 */
public class DescargarImagenAsyncTask extends AsyncTask<String, Integer, Bitmap> {

    private ProgressDialog progreso;

    private ImageView destino;

    // Al no estar manejado (Manifest) puedo definir yo un constructor
    public DescargarImagenAsyncTask(ProgressDialog progreso, ImageView destino) {
        this.progreso = progreso;
        this.destino = destino;
    }

    // Unico método que se ejecuta en el hilo Secundario

    @Override
    protected Bitmap doInBackground(String... params) {
        // Realizar la tarea de larga duración

        try {
            URL url = new URL(params[0]);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            int tamanioImagen = con.getContentLength();
            byte[] imagen = new byte[tamanioImagen];
            byte[] buffer = new byte[1024];

            InputStream is = con.getInputStream();

            for (int bytesTotalesLeidos = 0; bytesTotalesLeidos < tamanioImagen; ) {
                int bytesLeidos = is.read(buffer);

                System.arraycopy(buffer, 0, imagen, bytesTotalesLeidos, bytesLeidos);

                bytesTotalesLeidos += bytesLeidos;

                //Para actualizar el progreso
                publishProgress(bytesTotalesLeidos * 100 / tamanioImagen);
            }

            return BitmapFactory.decodeByteArray(imagen, 0, tamanioImagen);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Métodos que se ejecutan en el Hilo principal


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // Inicializamos
        progreso.setProgress(0);
        // No se puede hacer en el onBackGround
        progreso.show();

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        progreso.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if (bitmap != null) {
            destino.setImageBitmap(bitmap);
        }
        progreso.hide();
    }
}
