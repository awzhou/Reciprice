package com.example.reciprice;

import android.app.Application;

import com.backendless.Backendless;

public class RecipriceApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // initialize Backendless connection
        Backendless.initApp(this, com.example.reciprice.Credentials.APP_ID, com.example.reciprice.Credentials.API_KEY);
    }
}
