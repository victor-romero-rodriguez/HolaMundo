package com.example.tarde.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.tarde.INoticiasProviderUtil;

public class NoticiasProvider extends ContentProvider implements INoticiasProviderUtil {

    private static final String TABLA = "NOTICIAS";
    private static final int CODE_NOTICIAS = 1;
    private static final int CODE_NOTICIA = 2;
    private static final int CODE_AUTORES = 3;
    private static final int CODE_AUTOR = 4;

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private Context context;
    private SQLiteDatabase db;

    static {
        // Para crear el patrón de URI que se pretende resolver
        // La autoridad
        //content://com.curso.android.noticias/Noticia --> Afectan a todos el contenido
        matcher.addURI(INoticiasProviderUtil.NOTICIAS_AUTHORITY, INoticiasProviderUtil.NOTICIAS_ENTIDAD, CODE_NOTICIAS);

        // Se acaban definiendo 2 URL's
        //content://com.curso.android.noticias/Noticia/abc --> Afecta solo al item con ID = 1s
        // ..Noticia/# --> Indica número
        // ..Noticia/* --> Indica texto
        matcher.addURI(INoticiasProviderUtil.NOTICIAS_AUTHORITY, INoticiasProviderUtil.NOTICIAS_ENTIDAD + "/*", CODE_NOTICIA);

        //content://com.curso.android.noticias/Autores --> Afectan a todos el contenido
        matcher.addURI(INoticiasProviderUtil.NOTICIAS_AUTHORITY, INoticiasProviderUtil.AUTOR_ENTIDAD, CODE_AUTORES);
        //content://com.curso.android.noticias/Autor/1 --> Afecta solo al item con ID = 1s
        matcher.addURI(INoticiasProviderUtil.NOTICIAS_AUTHORITY, INoticiasProviderUtil.AUTOR_ENTIDAD + "/#", CODE_AUTOR);

        //content://com.curso.android.noticias/Autor/Noticia
        //matcher.addURI(NOTICIAS_AUTHORITY, AUTOR_ENTIDAD + "/" + NOTICIAS_ENTIDAD, 5);
    }



    @Override
    public boolean onCreate() {

        // Inicializamos
        context = getContext();

        NoticiasSQLiteOpenHelper noticiasSQLiteOpenHelper = new NoticiasSQLiteOpenHelper(context, "NoticiasDB.s3db", null, getContext().getResources().getInteger(R.integer.DatabaseVersion));

        db = noticiasSQLiteOpenHelper.getWritableDatabase();

        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        switch (matcher.match(uri)) {
            case CODE_NOTICIA:
                // Me da el último elemento del Path, de la URI
                String id = uri.getLastPathSegment();
                // Devuelve el número de registros afectados
                String whereClause = INoticiasProviderUtil.CAMPO_ID + " = ?";
                String[] whereArgs = {id};
                return db.delete(NoticiasProvider.TABLA, whereClause, whereArgs);
            case CODE_NOTICIAS:
                // Porque afecta a toda la tabla
                return db.delete(NoticiasProvider.TABLA, selection, selectionArgs);
            default:
                throw new UnsupportedOperationException("Operación No soportada");
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        //values.getAsString(CAMPO_TITULO +"1");

        switch (matcher.match(uri)) {
//            case CODE_NOTICIA:
//                //values.put(CAMPO_ID, valor);
//                db.insert(TABLA, CAMPO_TITULO, values);
            case CODE_NOTICIAS:
                db.insertOrThrow(TABLA, INoticiasProviderUtil.CAMPO_TITULO, values);
                // La URI, la tengo que sacar a partir de la URI de entrada y los values
                //String uriString = uri.toString().concat("/" + values.getAsString(CAMPO_ID));
                //return Uri.parse(uriString);
                //ContentUris.withAppendedId(uri,)
                return Uri.withAppendedPath(uri, values.getAsString(INoticiasProviderUtil.CAMPO_ID));
            default:
                throw new UnsupportedOperationException("Operación No soportada");
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (matcher.match(uri)) {
            case CODE_NOTICIA:
                String whereClause = INoticiasProviderUtil.CAMPO_ID + " = ?";
                String[] whereArgs = {uri.getLastPathSegment()};
                return db.query(false, TABLA, projection, whereClause, whereArgs, null, null, sortOrder, null);
            case CODE_NOTICIAS:
                return db.query(false, TABLA, projection, selection, selectionArgs, null, null, sortOrder, null);
            default:
                throw new UnsupportedOperationException("Operación No soportada");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        switch (matcher.match(uri)) {
            case CODE_NOTICIA:
                String id = uri.getLastPathSegment();
                // Devuelve el número de registros afectados
                String whereClause = INoticiasProviderUtil.CAMPO_ID + " = ?";
                String[] whereArgs = {id};
                return db.update(TABLA, values, whereClause, whereArgs);
            case CODE_NOTICIAS:
                return db.update(TABLA, values, selection, selectionArgs);
            case CODE_AUTOR:
                throw new UnsupportedOperationException("No se soporta el borrado de un Autor");
            case CODE_AUTORES:
                throw new UnsupportedOperationException("No se soporta el borrado de Autores de forma masiva");
            default:
                throw new UnsupportedOperationException("No se soporta el borrado de un Autor");
        }
    }

    @Override
    public String getType(Uri uri) {
        switch (matcher.match(uri)) {
            case CODE_NOTICIA:
                return "vnd.android.cursor.item/vnd." + INoticiasProviderUtil.NOTICIAS_AUTHORITY + "." + INoticiasProviderUtil.NOTICIAS_ENTIDAD;
            case CODE_NOTICIAS:
                return "vnd.android.cursor.dir/vnd." + INoticiasProviderUtil.NOTICIAS_AUTHORITY + "." + INoticiasProviderUtil.NOTICIAS_ENTIDAD;
            case CODE_AUTOR:
                return "vnd.android.cursor.item/vnd." + INoticiasProviderUtil.NOTICIAS_AUTHORITY + "." + INoticiasProviderUtil.AUTOR_ENTIDAD;
            case CODE_AUTORES:
                return "vnd.android.cursor.dir/vnd." + INoticiasProviderUtil.NOTICIAS_AUTHORITY + "." + INoticiasProviderUtil.AUTOR_ENTIDAD;
            default:
                throw new UnsupportedOperationException("Tipo MIME no soportado");
        }
    }
}
