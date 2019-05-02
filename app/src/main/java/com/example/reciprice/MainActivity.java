package com.example.reciprice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.backendless.Backendless;

public class MainActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fm = getSupportFragmentManager();
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    fm.beginTransaction().replace(R.id.container, new DisplayFragment()).commit();
                    return true;
                case R.id.navigation_saved:
                    fm.beginTransaction().replace(R.id.container, new SaveFragment()).commit();
                    return true;
                case R.id.navigation_login:
                    if(!(Backendless.UserService.isValidLogin())) {
                        fm.beginTransaction().replace(R.id.container, new LoginFragment()).commit();
                        return true;
                    }

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_search);

        if (Backendless.UserService.isValidLogin()) {

            // get menu from navigationView
            Menu menu = navigation.getMenu();

            // find MenuItem you want to change
            MenuItem nav_account = menu.findItem(R.id.navigation_login);

            // set new title to the MenuItem
            nav_account.setTitle(R.string.title_logout);
        }
    }

}
