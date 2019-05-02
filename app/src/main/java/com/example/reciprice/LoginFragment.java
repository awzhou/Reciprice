package com.example.reciprice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class LoginFragment extends Fragment {

    private TextView textViewCreateAccount;
    private Button buttonLogin;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private TextView textViewTitle;

    public static final String EXTRA_USERNAME = "username";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        wireWidgets(rootView);

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
                //Intent createAccountIntent = new Intent(getContext(), CreateAccountFragment.class);

                // TODO: pass info betw fragments
//                String username = editTextUsername.getText().toString();
//                if(username.length() > 0) {
//                    createAccountIntent.putExtra(EXTRA_USERNAME, username);
//                }
//                startFragment(createAccountIntent);

                Fragment newFragment = new CreateAccountFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return rootView;
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

                        Toast.makeText(getActivity(),
                                "Logged In"
                                , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(getActivity(),
                                fault.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void wireWidgets(View rootView) {
        textViewCreateAccount = rootView.findViewById(R.id.textView_login_create);
        buttonLogin = rootView.findViewById(R.id.button_login_login);
        editTextPassword = rootView.findViewById(R.id.editText_login_pass);
        editTextUsername = rootView.findViewById(R.id.editText_login_user);
        textViewTitle = rootView.findViewById(R.id.textView_login_title);
    }
}
