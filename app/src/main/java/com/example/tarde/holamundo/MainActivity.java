package com.example.tarde.holamundo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    public static final int REQUEST_CODE_CON_RESULTADO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handler del botón
        Button boton = (Button)findViewById(R.id.button);



        // Para crearlo fuera
        //boton.setOnClickListener(new SaludoOncLickListener());

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                // Para crear una nueva actividasd, tenemos que crear el objecto génerico, en este caso el Intent()
                Intent intent = new Intent(MainActivity.this, SecondaryActivity.class);

                // Si queremos pasar parámetros
                // Objeto Serializable, es una Interfaz de marcado, se usa para trasladar un objeto a otra máquina virtual, y que la otra máquina lo pueda construir en memoría
                intent.putExtra("dato", "Mi nombre es Victor");

                // Ahora si al pulsar el botón se habre una nueva ventana
                startActivity(intent);

                // Esto es para la navegación


            }
        });


        // Botón con resultado
        Button bResultado = (Button)findViewById(R.id.btConResultado);

        bResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Para crear una nueva actividad, tenemos que crear el objecto génerico, en este caso el Intent()
                Intent iConResultado = new Intent(MainActivity.this, ConResultadoActivity.class);

                // Nos proporciona un mécanismo para lanzar un evento cuando la actividad secundaria finalice
                startActivityForResult(iConResultado, REQUEST_CODE_CON_RESULTADO);
            }
        });

        // Botón con resultado
        Button bImplicita = (Button)findViewById(R.id.bImplicita);

        bImplicita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Lanzamos una intención Implicita, la misma constante que creamos en el AndroidManifest
                Intent iImplicita = new Intent("com.example.tarde.holamundo.IMPLICITA");

                // Se podría haber hecha a pelo, indicando a posterior la categoría.

                startActivity(iImplicita);
                // Ahora nos va ha abrir una acción pero no estamos seguros de cual, si no existe vamos a tener un error.
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //  Hay una única método, con lo que debemos discriminar quien es el que devuelve el control a la actividad principal
         if(requestCode == REQUEST_CODE_CON_RESULTADO){
             if(resultCode == Activity.RESULT_OK){
                 String sResultado = data.getStringExtra("resultado");

                 // Elemento de aviso
                 Toast.makeText(this, "El resultado es: " + sResultado, Toast.LENGTH_LONG).show();

             }else{

                 Log.i(MainActivity.class.getName(), "El resultado no ha sido correcto");
             }
         }
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
