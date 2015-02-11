package com.example.tarde.servicioremoto;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private Messenger messenger;

    private IServicioSaludador servicioSaludador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       switch (item.getItemId()) {

            case R.id.action_bind_services:
                Intent intent = new Intent("com.example.tarde.servicioremoto.SALUDO");
                bindService(intent, new RemoteServiceConnection(), Context.BIND_AUTO_CREATE);
                break;

            case R.id.action_hi_services:
                Message msg = Message.obtain(null, 0);
                Bundle bundle = new Bundle();
                bundle.putString("saludo", "Saludos cordiales en Remoto");
                msg.setData(bundle);
                try {
                    messenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;

           case R.id.action_bind_saludador:
               Intent intent2 = new Intent("com.example.tarde.servicio.saludador.SALUDO");
               bindService(intent2, new SaludadorSeriveConnection(), Context.BIND_AUTO_CREATE);
               break;

           case R.id.action_hi_saludador:
               break;
        }

        return super.onOptionsItemSelected(item);
    }

    class RemoteServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
        }
    }

    class SaludadorSeriveConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
           servicioSaludador = IServicioSaludador.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            servicioSaludador = null;
        }
    }
}
