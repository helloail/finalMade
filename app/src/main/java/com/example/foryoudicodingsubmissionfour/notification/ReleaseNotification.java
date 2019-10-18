package com.example.foryoudicodingsubmissionfour.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.helper.Config;
import com.example.foryoudicodingsubmissionfour.helper.MyApplication;
import com.example.foryoudicodingsubmissionfour.model.FilmInit;
import com.example.foryoudicodingsubmissionfour.model.notification.NotificationInit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReleaseNotification extends BroadcastReceiver {

    private static final CharSequence CHANNEL_NAME = "Release channel";
    private final static String GROUP_KEY_EMAILS = "group_key_emails";
    private int idNotif = 0;
    private int maxNotif = 2;
    private List<NotificationInit> notificationInits = new ArrayList<>();
    private final int Code_Release = 12;
    private String TAG  = "release";

    public ReleaseNotification() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        reqNotif(context);
    }


    private void reqNotif(final Context context){

            AndroidNetworking.get(Config.url_rilis_notif)
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (response == null) {
                                Toast.makeText(context,  "Cannot Conect To Server", Toast.LENGTH_LONG).show();
                                return;
                            }
                            Log.d("JsonNotif", response.toString());

                            try {
                                JSONArray jsonArrayData = response.getJSONArray("results");
                                if (jsonArrayData.length() <= 0) {
                                    Toast.makeText(context, "no data available", Toast.LENGTH_LONG).show();
                                    Log.d("idnotif","no nootification");
                                    return;
                                }
                                for (int i = 0; i < jsonArrayData.length(); i++) {
                                    JSONObject jsonObjectData = jsonArrayData.getJSONObject(i);
                                    NotificationInit data = new Gson().fromJson(jsonObjectData.toString(), new TypeToken<NotificationInit>() {
                                    }.getType());
                                    notificationInits.add(data);
                                    sendnotif(context);
                                    idNotif++;
                                    Log.d("idnotif", String.valueOf(idNotif));
                                }

                            } catch (JSONException e) {

                            }
                        }


                        @Override
                        public void onError(ANError error) {
                            if (error.getErrorCode() != 0) {

                                Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                                Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                                Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                                // get parsed error object (If ApiError is your class)
//                            ApiError apiError = error.getErrorAsObject(ApiError.class);
                            } else {
                                // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                                Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            }
                        }
                    });

    }

    private void sendnotif(Context context){

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_background);
        NotificationCompat.Builder mBuilder;
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        String CHANNEL_ID = "channel_01";
        if (idNotif < maxNotif) {
            mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle("Release Today " + notificationInits.get(idNotif).getTitle())
                    .setContentText(notificationInits.get(idNotif).getTitle())
                    .setSmallIcon(R.drawable.ic_notifications_24px)
                    .setLargeIcon(largeIcon)
                    .setGroup(GROUP_KEY_EMAILS)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                    .setSound(alarmSound)
                    .setAutoCancel(true);

        } else {
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
                    .addLine("Release Today " + notificationInits.get(idNotif).getTitle())
                    .addLine("Release Today " + notificationInits.get(idNotif - 1).getTitle())
                    .setBigContentTitle(idNotif + " new release")
                    .setSummaryText("Release Today");
            mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle("Release Today " + notificationInits.get(idNotif).getTitle())
                    .setContentText(notificationInits.get(idNotif).getTitle())
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                    .setSound(alarmSound)
                    .setSmallIcon(R.drawable.ic_notifications_24px)
                    .setGroup(GROUP_KEY_EMAILS)
                    .setGroupSummary(true)
                    .setStyle(inboxStyle)
                    .setAutoCancel(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            mBuilder.setChannelId(CHANNEL_ID);

            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = mBuilder.build();

        if (mNotificationManager != null) {
            mNotificationManager.notify(idNotif, notification);
        }
    }

    public void setReminder(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReleaseNotification.class);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 0);


        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Code_Release, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (alarmManager != null)   {

            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);

        }
    }

    public void cancelReminder(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReleaseNotification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Code_Release, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntent.cancel();
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

}
