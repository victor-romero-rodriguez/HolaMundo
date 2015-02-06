package com.example.tarde.intentservice;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;


public class SaludadorIntentService extends IntentService {

    private static final String ACTION_SALUDAR = "com.example.tarde.intentservice.action.SALUDAR";
    private static final String ACTION_DESPEDIR = "com.example.tarde.intentservice.action.DESPEDIR";
    public static final String BROADCAST_ACTION_SALUDAR = "com.example.tarde.intentservice.broadcast.action.SALUDAR";
    public static final String BROADCAST_ACTION_DESPEDIR = "com.example.tarde.intentservice.broadcast.action.DESPEDIR";

    public static final String EXTRA_PARAM_NOMBRE = "nombre";


    public SaludadorIntentService() {
        super("SaludadorIntentService");
    }

    public static void startActionSaludar(Context context, String nombre) {
        Intent intent = new Intent(context, SaludadorIntentService.class);
        intent.setAction(ACTION_SALUDAR);
        intent.putExtra(EXTRA_PARAM_NOMBRE, nombre);
        context.startService(intent);
    }

    public static void startActionDespedir(Context context, String nombre) {
        Intent intent = new Intent(context, SaludadorIntentService.class);
        intent.setAction(ACTION_DESPEDIR);
        intent.putExtra(EXTRA_PARAM_NOMBRE, nombre);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SALUDAR.equals(action)) {
                final String nombre = intent.getStringExtra(EXTRA_PARAM_NOMBRE);
                handleActionSaludar(nombre);
            } else if (ACTION_DESPEDIR.equals(action)) {
                final String nombre = intent.getStringExtra(EXTRA_PARAM_NOMBRE);
                handleActionDespedir(nombre);
            }
        }
    }

    private void handleActionSaludar(String nombre) {
        // No puede ir un Toast, porque estamos en un hilo secundario

        // Aquí entran en acción los mensajes de tipo BroadCast, que al final de todos es un evento
        Intent intent = new Intent(BROADCAST_ACTION_SALUDAR);
        intent.putExtra(EXTRA_PARAM_NOMBRE, nombre);
        sendBroadcast(intent);
    }

    private void handleActionDespedir(String nombre) {
        Intent intent = new Intent(BROADCAST_ACTION_DESPEDIR);
        intent.putExtra(EXTRA_PARAM_NOMBRE, nombre);
        sendBroadcast(intent);
    }
}
