package com.example.reciprice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.reciprice.R;

public class CreateAccountFragment extends Fragment {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextEmail;
    private Button buttonCreateAccount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_account, container, false);

        wireWidgets(rootView);
        //prefillUserName();

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAccountOnBackendless();
            }
        });

        return rootView;
    }

    // TODO: Send info between fragments
//    private void prefillUserName() {
//        String username = getIntent().getStringExtra(LoginFragment.EXTRA_USERNAME);
//        if(username != null) {
//            editTextUsername.setText(username);
//        }
//    }

    private void registerAccountOnBackendless() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();
        String username = editTextUsername.getText().toString();
        if (allFieldsValid(username, confirmPassword, password)) {
            BackendlessUser user = new BackendlessUser();
            user.setProperty("username", username);
            user.setEmail(email);
            user.setPassword(password);
            Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser response) {
                    Toast.makeText(getContext(),  "You are registered", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void handleFault(BackendlessFault fault) {
                    Toast.makeText(getContext(),  fault.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            //sendInfo();
        } else {
            Toast.makeText(getContext(), "Your confirm password must match with your password or you must include a username", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean allFieldsValid(String username, String confirmPassword, String password) {
        if (confirmPassword.equals(password) && username.length() > 0) {
            return true;
        }
        return false;
    }

    // TODO: Send info betw fragments
//    private void sendInfo() {
//        Intent intent = new Intent();
//        //put data into intent with key value
//        intent.putExtra("username", editTextUsername.getText().toString());
//        intent.putExtra("password", editTextPassword.getText().toString());
//        //set result so that activity is able to access intent
//        setResult(RESULT_OK, intent);
//    }


    private void wireWidgets(View rootView) {
        editTextUsername = rootView.findViewById(R.id.editText_create_user);
        editTextPassword = rootView.findViewById(R.id.editText_create_pass);
        editTextConfirmPassword = rootView.findViewById(R.id.editText_create_confirm);
        editTextEmail = rootView.findViewById(R.id.editText_create_email);
        buttonCreateAccount = rootView.findViewById(R.id.button_create_submit);
    }
}
