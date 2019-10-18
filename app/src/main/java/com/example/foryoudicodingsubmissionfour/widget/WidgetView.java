package com.example.foryoudicodingsubmissionfour.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.helper.Config;
import com.example.foryoudicodingsubmissionfour.roomdb.Favorite_FilmInit;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.provider.Settings.System.CONTENT_URI;
import static com.example.foryoudicodingsubmissionfour.helper.MyApplication.db;

public class WidgetView implements RemoteViewsService.RemoteViewsFactory {

    private List<Favorite_FilmInit> favorite_filmInits = new ArrayList<>();
    private Context mContext;
    private int mAppWidgetId;

    public WidgetView(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        favorite_filmInits = db.filmInitDao().getAll();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return favorite_filmInits.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.item_widget);

        try {
            Bitmap bitmap = Glide.with(mContext)
                    .asBitmap()
                    .load(Config.url_image+favorite_filmInits.get(position).getBackdrop_path())
                    .submit(512, 512)
                    .get();

            rv.setImageViewBitmap(R.id.itemwidget, bitmap);

        } catch (InterruptedException | ExecutionException e) {
            Log.d("Failed to load Widget", "Error");
        }

        Bundle extras = new Bundle();
        extras.putInt(WidgetReceiver.This_Item, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.itemwidget, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}