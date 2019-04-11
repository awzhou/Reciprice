package com.example.swimtracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView mTextMessage;
    Fragment newFragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_add:
                    //launch the AddFragment
                    newFragment = new AddFragment();
                    return true;
                case R.id.navigation_view:
                    //launch the ViewFragment
                    //newFragment = new ViewFragment();
                    return true;
            }
            return false;


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //if the fragment isn't null, we'll switch fragments
        if (newFragment != null) {
            FragmentManager fm = getSupportFragmentManager();

            //in the builder pattern, you can keep calling methods in a row because it returns the
            //same data type. Before the commit, we could  set the transition, change the back
            //stack, etc.
            fm.beginTransaction()
                    .replace(R.id.container_main, newFragment)
                    .commit();
        }

    }

}
