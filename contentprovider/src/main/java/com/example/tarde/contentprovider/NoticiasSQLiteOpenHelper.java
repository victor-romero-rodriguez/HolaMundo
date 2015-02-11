package com.example.tarde.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tarde on 09/02/2015.
 */
public class NoticiasSQLiteOpenHelper extends SQLiteOpenHelper {

    private Context context;

    public NoticiasSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        // Referencia al contexto para poder recoger las recursos referentes al script
        this.context = context;
    }

    // Gestión o Administración de la BBDD
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQLiteDatabase semejante al Session de Hibernate
        // crear todas las instancias necesarias para dejar la BBDD en el estado necesario para empezar a trabajar con ella.


        ejecutarScript(db, R.array.scriptCreate);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

       switch (oldVersion){
           case 1:
               ejecutarScript(db, R.array.scriptUpgrade_1_3);
               break;
           case 2:
               ejecutarScript(db, R.array.scriptUpgrade_2_3);
               break;
       }


    }

    private void ejecutarScript(SQLiteDatabase db, int resScript) {

        String[] scripts = context.getResources().getStringArray(resScript);

        db.beginTransaction();

        try {

            for (String sentencia : scripts) {
                db.execSQL(sentencia);
            }

            // Marcamos que la transacción ha sido buena
            db.setTransactionSuccessful();

        } finally {
            db.endTransaction();
        }
    }
}
