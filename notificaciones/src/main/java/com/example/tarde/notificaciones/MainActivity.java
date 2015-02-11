package com.example.tarde.notificaciones;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private Notification.Builder builderBigPicture;
    private Notification.Builder builderProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builderBigPicture = new Notification.Builder(this);


        // Intent implicito
        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builderBigPicture.setTicker("Aviso")
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("Notificación de aviso")
                .setContentText("Descripción de la notificación")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.star_on))
                .setStyle(new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), android.R.drawable.star_on)))
                        //.setStyle(new Notification.BigTextStyle().bigText("Descripción de la notificación"))
                .setContentIntent(pendingIntent);


        builderProgress = new Notification.Builder(this);
        builderProgress.setTicker("Progreso")
                .setContentTitle("Cargando...")
                .setSmallIcon(android.R.drawable.star_on);
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

        int i = 0;

        NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //noinspection SimplifiableIfStatement
        if (id == R.id.notificacion_bigPicture) {

            nManager.notify(1, builderBigPicture.build());

            return true;
        } else if (id == R.id.notificacion_Progress) {

            builderProgress.setProgress(100, i, false);
            i +=10;
            Notification notificacion = builderProgress.build();

            nManager.notify(2, notificacion);

            return true;
        } else if (id == R.id.notificacion_cancel_progress) {

            nManager.cancel(2);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
