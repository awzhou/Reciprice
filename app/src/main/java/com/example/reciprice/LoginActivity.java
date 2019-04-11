package com.example.reciprice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    private TextView textViewCreateAccount;
    private Button buttonLogin;
    private EditText editTextUsername;
    private EditText editTextPassword;

    public static final String EXTRA_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        wireWidgets();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: login to a different activity somewhere
                loginToBackendless();
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
        Backendless.UserService.login(login, password,
                new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        // Start the new activity here because
                        // this method is called when the login is complete
                        // and successful

                        Toast.makeText(LoginActivity.this,
                                response.getEmail() + " Logged In"
                                , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(LoginActivity.this,
                                fault.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void wireWidgets() {
        textViewCreateAccount = findViewById(R.id.textView_login_create);
        buttonLogin = findViewById(R.id.button_login_login);
        editTextPassword = findViewById(R.id.editText_login_pass);
        editTextUsername = findViewById(R.id.editText_login_user);
    }
}
