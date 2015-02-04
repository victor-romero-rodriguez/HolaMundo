package com.example.tarde.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // · Formas para obtener las Preferencias
        // 3
        // Código para indicar el modo en que están las preferencias (privadas, públicas, etc...)
        //getPreferences(MODE_PRIVATE);
        // 2
        // Las siguiente recomendada
        //getSharedPreferences("",0);
        // 1
        // Esta es la recomendada, con las que trabajar
        /***
           SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

         SharedPreferences.Editor edit = preferences.edit();

         edit.putString("nombre", "Victor");

         edit.commit();


         Toast.makeText(this, preferences.getString("nombre", ""), Toast.LENGTH_LONG).show();
         *
         */


        View view = findViewById(R.id.btPreferencias);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);

                startActivity(intent);**/

                // Ahora aquí definiremos las preferencias a leer

                SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                // Par obtener cualquier tipo de recurso "getResources().getString(...)", es una interfaz directa a "res"
                //String msj = preferences.getString(getResources().getString(R.string.key_nombre),"");
                String msj2 = preferences.getString(getResources().getString(R.string.key_colores),"");

                Toast.makeText(MainActivity.this, msj2, Toast.LENGTH_LONG).show();

            }
        });



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

            // Handle de manejar la opción de pulsar en una de las opciones del menú

            Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);

            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
