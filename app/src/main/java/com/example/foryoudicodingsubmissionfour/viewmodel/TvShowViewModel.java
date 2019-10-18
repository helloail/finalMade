package com.example.foryoudicodingsubmissionfour.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.foryoudicodingsubmissionfour.helper.Config;
import com.example.foryoudicodingsubmissionfour.model.TvShowInit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TvShowViewModel  extends ViewModel {
    String TAG  = "view model movie";

    private MutableLiveData<ArrayList<TvShowInit>> listtvshow = new MutableLiveData<>();

    public void setTvShow(){

        final ArrayList<TvShowInit> listItems = new ArrayList<>();

        AndroidNetworking.get(Config.url_tvshow)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("JsonTvShow", response.toString());

                        try {
                            JSONArray jsonArrayData = response.getJSONArray("results");


                            for (int i = 0; i < jsonArrayData.length(); i++) {
                                JSONObject jsonObjectData = jsonArrayData.getJSONObject(i);
                                TvShowInit data = new Gson().fromJson(jsonObjectData.toString(), new TypeToken<TvShowInit>() {
                                }.getType());
                                listItems.add(data);
                            }
                            listtvshow.postValue(listItems);

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

    public LiveData<ArrayList<TvShowInit>> getListTvShow() {
        return listtvshow;
    }
}
