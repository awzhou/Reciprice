package com.example.basiclogins;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                    Toast.makeText(LoginActivity.this, "Under Construction", Toast.LENGTH_SHORT).show();
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

    private void wireWidgets() {
        editTextUsername = findViewById(R.id.editText_login_username);
        editTextPassword = findViewById(R.id.editText_login_password);
        buttonLogin = findViewById(R.id.button_login_login);
        textViewCreateAccount = findViewById(R.id.textView_login_create);
    }
}
