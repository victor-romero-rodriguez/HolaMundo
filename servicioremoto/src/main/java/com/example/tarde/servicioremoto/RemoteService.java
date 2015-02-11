package com.example.tarde.servicioremoto;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class RemoteService extends Service {

    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        // Para las tareas en el Servidor (Handler)
        // Para las tareas en el Clienta (Binder)
        Messenger messenger = new Messenger(new RemoteHandler());

        // El messenger del Servidor, comparte su Binder con el del Cliente, para que este cree su Messenger
        return messenger.getBinder();
    }


    class RemoteHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 0:
                    String saludo = msg.getData().getString("saludo");
                    saludo(saludo);
                    break;
                case 1:
                    break;
            }
        }

    }

    public void saludo(String sSaludo){
        Toast.makeText(this, sSaludo, Toast.LENGTH_LONG).show();
    }
}
