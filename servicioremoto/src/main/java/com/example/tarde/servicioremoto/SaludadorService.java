package com.example.tarde.servicioremoto;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class SaludadorService extends Service {

    private final IServicioSaludador.Stub mBinder = new IServicioSaludador.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };

    //@Override
    // public IBinder onBind(Intent intent) {
//
    //IServicioSaludador saludador = IServicioSaludador.Stub.asInterface(null);
    //
    //saludador.basicTypes();
//
    // La parte Servidor, del servicio
    //   IServicioSaludador.Stub stub = new IServicioSaludador.Stub() {
    //     @Override
    //   public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
    //     Toast.makeText(SaludadorService.this, "El entero recibido es: " + anInt, Toast.LENGTH_LONG).show();
    //}
    // };
//
    // return stub;
    //}

    public SaludadorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Return the interface
        return mBinder;
    }

}
