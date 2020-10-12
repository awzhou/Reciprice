package com.example.reciprice.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.reciprice.R;
import com.example.reciprice.model.Items;
import com.example.reciprice.model.Offer;
import com.example.reciprice.model.ProductResponse;
import com.example.reciprice.repo.ProductService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IngredientActivity extends AppCompatActivity {


    private String ingredient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);

        ingredient = getIntent().getStringExtra("Ingredient");
        Intent intent = getIntent();

        searchUpc();
    }

    private void searchUpc() {
        try {
            HttpResponse<JsonNode> response = Unirest.post("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/food/ingredients/map")
                    .header("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "4eced43f80mshed00119a7188f99p1c9537jsn1d729dfb7bae")
                    .header("Content-Type", "application/json")
                    .body("{  \"ingredients\": [\"" +ingredient+"\"],}")
                    .asJson();
            Log.e("upcresponse",response.getBody().toString());
            Log.e("ingredient",ingredient);
            JSONArray jsonArray = response.getBody().getArray();
            ArrayList<String> upcs = new ArrayList<>();
            if (jsonArray != null) for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    upcs.add(jsonArray.getString(i));
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }

            Log.e("upcs", upcs.toString());

        }catch(UnirestException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ((item.getItemId())){
            case R.id.menu_item_home:
                Intent intent = new Intent(IngredientActivity.this, MainActivity.class);
                finish();
                startActivity(intent);

//                Intent mStartActivity = new Intent(IngredientActivity.this, MainActivity.class);
//                int mPendingIntentId = 123456;
//                PendingIntent mPendingIntent = PendingIntent.getActivity(IngredientActivity.this, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
//                AlarmManager mgr = (AlarmManager)IngredientActivity.this.getSystemService(IngredientActivity.this.ALARM_SERVICE);
//                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
//                System.exit(0);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
