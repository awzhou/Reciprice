package com.example.reciprice;

import android.app.Application;

import com.backendless.Backendless;
import com.example.reciprice.model.Credentials;

public class RecipriceApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // initialize Backendless connection
        Backendless.initApp(this, Credentials.BACKENDLESS_APP_ID, Credentials.BACKENDLESS_API_KEY);
    }
}
