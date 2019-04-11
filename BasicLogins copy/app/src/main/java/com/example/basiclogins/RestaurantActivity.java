package com.example.basiclogins;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class RestaurantActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextCuisine;
    private EditText editTextAddress;
    private RatingBar ratingBar;
    private SeekBar seekBarPrice;
    private Button buttonSave;

    private Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        wireWidgets();
        setOnClickListeners();
        prefillFields();

    }

    private void setOnClickListeners() {
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToBackendless();
//                Intent intent = new Intent(RestaurantActivity.this, RestaurantListActivity.class);
//                startActivity(intent);
                finish();

            }
        });
    }

    private void prefillFields() {
        //check if there's an Intent
        Intent restaurantIntent = getIntent();
        restaurant = restaurantIntent.getParcelableExtra(RestaurantListActivity.EXTRA_RESTAURANT);
        if (restaurant != null) {
            editTextName.setText(restaurant.getName());
            editTextCuisine.setText(restaurant.getAddress());
            editTextAddress.setText(restaurant.getAddress());
            ratingBar.setRating((float) restaurant.getRating());
            seekBarPrice.setProgress(restaurant.getPrice());
        }
        //if there is, then get the Restaurant object & populate the fields
    }

    public void saveToBackendless()
    {

        String name = editTextName.getText().toString();
        String cuisine = editTextCuisine.getText().toString();
        String address = editTextAddress.getText().toString();
        double rating = ratingBar.getRating();
        int price = seekBarPrice.getProgress();

        if (restaurant != null) {
            restaurant.setName(name);
            restaurant.setCuisine(cuisine);
            restaurant.setAddress(address);
            restaurant.setRating(rating);
            restaurant.setPrice(price);
        }
        else {
            restaurant = new Restaurant(name, cuisine, address, rating, price);
        }



        // save object asynchronously
        Backendless.Persistence.save(restaurant, new AsyncCallback<Restaurant>() {
            public void handleResponse(Restaurant response)
            {
                // new Contact instance has been saved
                Toast.makeText(RestaurantActivity.this, response.getName() + " Successfully Added", Toast.LENGTH_SHORT).show();
            }

            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Toast.makeText(RestaurantActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void wireWidgets() {
        editTextName = findViewById(R.id.editText_restaurant_name);
        editTextCuisine = findViewById(R.id.editText_restaurant_cuisine);
        editTextAddress = findViewById(R.id.editText_restaurant_address);
        ratingBar = findViewById(R.id.ratingBar_restaurant_rating);
        seekBarPrice = findViewById(R.id.seekBar_restaurant_price);
        buttonSave = findViewById(R.id.button_restaurant_save);
    }
}
