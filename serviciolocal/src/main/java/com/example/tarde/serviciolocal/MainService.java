package com.example.tarde.serviciolocal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by tarde on 05/02/2015.
 */
public class MainService extends Service{

    // Se ejecuta ern segundo plano pero en el Hilo principal


    public MainService() {
    }

    // Responden a eventos que se producen desde el cliente
    // Siendo los eventos START, STOP, BIND

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Arrancado el Servicio!", Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Enlazado el Servicio!", Toast.LENGTH_LONG).show();
        MainBinder binder = new MainBinder(this);
        return binder;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Destruido el Servicio!", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    // A traves del Binder, tenemos acceso a las funcionalidades del servicio

    // Metodo publico accesible desde el cliente
    public void saludo(){
        Toast.makeText(this, "Hola Mundo!!!!!!!!", Toast.LENGTH_LONG).show();
    }

}
