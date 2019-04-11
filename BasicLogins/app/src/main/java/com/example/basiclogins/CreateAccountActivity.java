package com.example.basiclogins;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText editTextFirstname;
    private EditText editTextLastname;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextConfirmpassword;
    private EditText editTextEmail;
    private Button buttonCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account2);

        wireWidgets();

        editTextUsername.setText(getIntent().getStringExtra(LoginActivity.EXTRA_USERNAME));

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextFirstname.getText().toString().length() == 0) {
                    Toast.makeText(CreateAccountActivity.this, "Please enter your first name.", Toast.LENGTH_SHORT).show();
                }
                else if(editTextLastname.getText().toString().length() == 0) {
                    Toast.makeText(CreateAccountActivity.this, "Please enter your last name.", Toast.LENGTH_SHORT).show();
                }
                else if(editTextUsername.getText().toString().length() == 0) {
                    Toast.makeText(CreateAccountActivity.this, "Please enter a username.", Toast.LENGTH_SHORT).show();
                }
                else if(editTextPassword.getText().toString().length() == 0) {
                    Toast.makeText(CreateAccountActivity.this, "Please enter a password.", Toast.LENGTH_SHORT).show();
                }
                else if(editTextConfirmpassword.getText().toString().length() == 0) {
                    Toast.makeText(CreateAccountActivity.this, "Please confirm your password.", Toast.LENGTH_SHORT).show();
                }
                else if(editTextEmail.getText().toString().length() == 0) {
                    Toast.makeText(CreateAccountActivity.this, "Please enter your email.", Toast.LENGTH_SHORT).show();
                }
                else if(!(editTextPassword.getText().toString().equals(editTextConfirmpassword.getText().toString()))) {
                    Toast.makeText(CreateAccountActivity.this, "Your passwords do not match.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CreateAccountActivity.this, "Under construction", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void wireWidgets() {
        editTextFirstname = findViewById(R.id.editText_create_firstname);
        editTextLastname = findViewById(R.id.editText_create_lastname);
        editTextUsername = findViewById(R.id.editText_create_username);
        editTextPassword = findViewById(R.id.editText_create_password);
        editTextConfirmpassword = findViewById(R.id.editText_create_confirmpassword);
        editTextEmail= findViewById(R.id.editText_create_email);
        buttonCreate = findViewById(R.id.button_create_create);
    }
}
