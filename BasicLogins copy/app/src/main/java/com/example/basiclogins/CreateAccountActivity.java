package com.example.basiclogins;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

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
                registerAccountOnBackendless();

            }
        });
    }

    private void registerAccountOnBackendless() {
        // verify all the fields are filled out and passwords are the same
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmpassword.getText().toString();
        String username = editTextUsername.getText().toString();
        String email = editTextEmail.getText().toString();
        String name = editTextFirstname.getText().toString() + " " + editTextLastname.getText().toString();

        if (allFieldsValid(password, confirmPassword, username, email, name)) {
            // make the registration call
            BackendlessUser user = new BackendlessUser();
            user.setProperty( "email", email );
            user.setPassword( password );
            user.setProperty("username", username);
            user.setProperty("name", name);

            Backendless.UserService.register( user, new AsyncCallback<BackendlessUser>()
            {
                public void handleResponse( BackendlessUser registeredUser )
                {
                    // user has been registered and now can login
                    Toast.makeText(CreateAccountActivity.this, registeredUser.getProperty("username") + " has registered.", Toast.LENGTH_SHORT).show();

                    // TODO would be nice to return username to login activity
                    Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

                public void handleFault( BackendlessFault fault )
                {
                    // an error has occurred, the error code can be retrieved with fault.getCode()
                    Toast.makeText(CreateAccountActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } );
        }

        // return to the LoginActivity in the handleResponse
    }

    private boolean allFieldsValid(String password, String confirmPassword, String username, String email, String name) {
        // TODO actually validate all the fields
        return password.equals(confirmPassword) && username.length() > 0;
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
