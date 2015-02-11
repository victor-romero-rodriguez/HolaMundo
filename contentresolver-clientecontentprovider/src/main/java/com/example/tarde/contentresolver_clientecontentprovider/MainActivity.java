package com.example.tarde.contentresolver_clientecontentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tarde.INoticiasProviderUtil;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver contentResolver = getContentResolver();

        ContentValues values = new ContentValues();
        values.put(INoticiasProviderUtil.CAMPO_ID, "mno");
        values.put(INoticiasProviderUtil.CAMPO_TITULO, "BIS BIS");
        values.put(INoticiasProviderUtil.CAMPO_CREADOR, "Victor");
        values.put(INoticiasProviderUtil.CAMPO_CONTENIDO, "Esta es la primera noticia que tenemos para insertar en la BBDD de noticias");
        values.put(INoticiasProviderUtil.CAMPO_DESCRIPCION, "Nueva noticia en la Base de Datos");
        values.put(INoticiasProviderUtil.CAMPO_LINK, "http://www.google.es");
        values.put(INoticiasProviderUtil.CAMPO_FECHA, new Date().getTime());


        Uri elementoInsertado = contentResolver.insert(INoticiasProviderUtil.NOTICIA_URI, values);

        //Toast.makeText(this, elementoInsertado.toString(), Toast.LENGTH_LONG).show();

        String[] projection = {INoticiasProviderUtil.CAMPO_ID, INoticiasProviderUtil.CAMPO_TITULO,
                INoticiasProviderUtil.CAMPO_DESCRIPCION, INoticiasProviderUtil.CAMPO_CREADOR,
                INoticiasProviderUtil.CAMPO_CONTENIDO, INoticiasProviderUtil.CAMPO_LINK,
                INoticiasProviderUtil.CAMPO_FECHA};

        Cursor cursor = contentResolver.query(elementoInsertado, projection, null, null, null);

        List<Noticia> resultado = cursorToNoticias(cursor);

        Toast.makeText(this, "Tamaño "+resultado.size() + resultado.get(0).toString(), Toast.LENGTH_LONG).show();

    }

    private List<Noticia> cursorToNoticias(Cursor cursor) {

        List<Noticia> resultado = new LinkedList<Noticia>();

        Noticia noticia;
        if (cursor.moveToFirst()) {
            do {

                noticia = new Noticia();

                if (cursor.getColumnIndex(INoticiasProviderUtil.CAMPO_ID) != -1) {
                    noticia.setId(cursor.getString(cursor.getColumnIndex(INoticiasProviderUtil.CAMPO_ID)));
                }
                if (cursor.getColumnIndex(INoticiasProviderUtil.CAMPO_TITULO) != -1) {
                    noticia.setTitulo(cursor.getString(cursor.getColumnIndex(INoticiasProviderUtil.CAMPO_TITULO)));
                }
                if (cursor.getColumnIndex(INoticiasProviderUtil.CAMPO_CREADOR) != -1) {
                    noticia.setCreador(cursor.getString(cursor.getColumnIndex(INoticiasProviderUtil.CAMPO_CREADOR)));
                }
                if (cursor.getColumnIndex(INoticiasProviderUtil.CAMPO_DESCRIPCION) != -1) {
                    noticia.setDescripcion(cursor.getString(cursor.getColumnIndex(INoticiasProviderUtil.CAMPO_DESCRIPCION)));
                }
                if (cursor.getColumnIndex(INoticiasProviderUtil.CAMPO_LINK) != -1) {
                    noticia.setLink(cursor.getString(cursor.getColumnIndex(INoticiasProviderUtil.CAMPO_LINK)));
                }
                if (cursor.getColumnIndex(INoticiasProviderUtil.CAMPO_FECHA) != -1) {
                    noticia.setFechaPublicacion(new Date(cursor.getLong(cursor.getColumnIndex(INoticiasProviderUtil.CAMPO_FECHA))));
                }
                if (cursor.getColumnIndex(INoticiasProviderUtil.CAMPO_CONTENIDO) != -1) {
                    noticia.setContenido(cursor.getString(cursor.getColumnIndex(INoticiasProviderUtil.CAMPO_CONTENIDO)));
                }
                resultado.add(noticia);

            } while (cursor.moveToNext());
        }

        return resultado;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
