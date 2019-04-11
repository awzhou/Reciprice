package com.example.basiclogins;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewCreateAccount;

    public static final String EXTRA_USERNAME = "the username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        wireWidgets();

        editTextUsername.requestFocus();

        // initiate Backendless connection
        Backendless.initApp(this, Credentials.APP_ID, Credentials.API_KEY);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if(username.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Please enter your username.", Toast.LENGTH_SHORT).show();
                }
                else if(password.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Please enter your password.", Toast.LENGTH_SHORT).show();
                }
                else {
                    loginToBackendless();
                }
            }
        });

        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent createAccountIntent = new Intent(LoginActivity.this, CreateAccountActivity.class);

                String username = editTextUsername.getText().toString();
                if(username.length() > 0) {
                    createAccountIntent.putExtra(EXTRA_USERNAME, username);
                }

                startActivity(createAccountIntent);
            }
        });
    }

    private void loginToBackendless() {
        String login = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        Backendless.UserService.login(login, password, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                // Start the new activity here because
                // this method is called when the login is complete
                // and successful

                Toast.makeText(LoginActivity.this, response.getProperty("username") + " logged in", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, RestaurantListActivity.class);
                startActivity(intent);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(LoginActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void wireWidgets() {
        editTextUsername = findViewById(R.id.editText_login_username);
        editTextPassword = findViewById(R.id.editText_login_password);
        buttonLogin = findViewById(R.id.button_login_login);
        textViewCreateAccount = findViewById(R.id.textView_login_create);
    }
}
