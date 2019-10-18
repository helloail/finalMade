package com.example.foryoudicodingsubmissionfour.provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;

import com.example.foryoudicodingsubmissionfour.roomdb.AppRoomFilm;
import com.example.foryoudicodingsubmissionfour.roomdb.Favorite_FilmInit;
import com.example.foryoudicodingsubmissionfour.roomdb.FilmInitDao;

import java.util.ArrayList;
import java.util.Objects;

public class MovieProvider extends ContentProvider {

    public static final String Table_name  = "favorite_filminit";

    public static final String AUTHORITY = "com.example.foryoudicodingsubmissionfour.provider";


    /** The match code for some items in the Menu table. */
    private static final int CODE_MENU_DIR = 1;

    /** The match code for an item in the Menu table. */
    private static final int CODE_MENU_ITEM = 2;

    private static final int CODE_DIR = 1;
    private static final int CODE_ITEM = 2;

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, Table_name, CODE_DIR);
        MATCHER.addURI(AUTHORITY, Table_name + "/*", CODE_ITEM);
    }

    @Override
    public boolean onCreate() {
//        database = AppRoomFilm.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(
            @NonNull Uri uri,
            @Nullable String[] projection,
            @Nullable String selection,
            @Nullable String[] selectionArgs,
            @Nullable String sortOrder) {

        final int code = MATCHER.match(uri);
        if (code == CODE_DIR || code == CODE_ITEM){
            final Context context = getContext();


            if (context == null) {
                return null;
            }
            Log.d("Tesis","tesis");


            Cursor cursor = null;
            FilmInitDao database = AppRoomFilm.getInstance(context).filmInitDao();


            if (code == CODE_DIR) {
                cursor = database.selectAll();
                Log.d("Tesisi","tesisi");

            } else {
                cursor = database.selectById(ContentUris.parseId(uri));
            }
            Objects.requireNonNull(cursor).setNotificationUri(context.getContentResolver(), uri);
            return cursor;
        }else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (MATCHER.match(uri)) {
            case CODE_DIR:
                Log.d("Tesisii","tesisii");
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + Table_name;
            case CODE_ITEM:
                Log.d("Tesisii","tesisii");
                return "vnd.android.cursor.item/" + AUTHORITY + "." + Table_name;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (MATCHER.match(uri)) {
            case CODE_MENU_DIR:
                final Context context = getContext();
                if (context == null) {
                    return null;
                }
                Log.d("Tesisii","tesisii");
                final long id = AppRoomFilm.getInstance(context).filmInitDao()
                        .insert(Favorite_FilmInit.fromContentValues(values));
                context.getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            case CODE_MENU_ITEM:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (MATCHER.match(uri)) {
            case CODE_MENU_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case CODE_MENU_ITEM:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                final int count = AppRoomFilm.getInstance(context).filmInitDao()
                        .deleteById(ContentUris.parseId(uri));
                context.getContentResolver().notifyChange(uri, null);
                return count;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (MATCHER.match(uri)) {
            case CODE_MENU_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case CODE_MENU_ITEM:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                final Favorite_FilmInit favorite_filmInit = Favorite_FilmInit.fromContentValues(values);
                favorite_filmInit.note_id = ContentUris.parseId(uri);
                final int count = AppRoomFilm.getInstance(context).filmInitDao()
                        .updatecon(favorite_filmInit);
                context.getContentResolver().notifyChange(uri, null);
                return count;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(
            @NonNull ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        final Context context = getContext();
        if (context == null) {
            return new ContentProviderResult[0];
        }
        final AppRoomFilm database = AppRoomFilm.getInstance(context);
        database.beginTransaction();
        try {
            final ContentProviderResult[] result = super.applyBatch(operations);
            database.setTransactionSuccessful();
            return result;
        } finally {
            database.endTransaction();
        }
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] valuesArray) {
        switch (MATCHER.match(uri)) {
            case CODE_MENU_DIR:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                final AppRoomFilm database = AppRoomFilm.getInstance(context);
                final Favorite_FilmInit[] menus = new Favorite_FilmInit[valuesArray.length];
                for (int i = 0; i < valuesArray.length; i++) {
                    menus[i] = Favorite_FilmInit.fromContentValues(valuesArray[i]);
                }
                return database.filmInitDao().insertAl(menus).length;
            case CODE_MENU_ITEM:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

}
