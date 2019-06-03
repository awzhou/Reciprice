package com.example.reciprice.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.reciprice.R;
import com.example.reciprice.model.Items;
import com.example.reciprice.model.Offer;
import com.example.reciprice.model.Product;
import com.example.reciprice.model.ProductResponse;
import com.example.reciprice.model.Upc;
import com.example.reciprice.repo.ProductService;
import com.example.reciprice.repo.UpcService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IngredientActivity extends AppCompatActivity{
    private RecyclerView recyclerViewUpcs;
    private UpcAdapter upcAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String ingredient;
    private UpcService upcService;
    private List<Product> upcs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);

        upcs = new ArrayList<>();

        recyclerViewUpcs = findViewById(R.id.recyclerView_upcs);

        layoutManager = new LinearLayoutManager(this);
        upcAdapter = new UpcAdapter(upcs);


        recyclerViewUpcs.setAdapter(upcAdapter);
        recyclerViewUpcs.setLayoutManager(layoutManager);

        ingredient = getIntent().getStringExtra("Ingredient");

        HashMap<String, String[]> json = new HashMap<>();
        json.put("ingredients", new String[] {ingredient});

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        upcService = retrofit.create(UpcService.class);
        upcService.getUpc(json).enqueue(new Callback<List<Upc>>() {
            @Override
            public void onResponse(Call<List<Upc>> call, Response<List<Upc>> response) {
                Log.d("IngredientActivity", "onResponse: " + response.body());
                List<Product> newUpcs = response.body().get(0).getProducts();
                //newUpcs.get(0).getTitle().trim();
                upcs.addAll(newUpcs);
                upcAdapter.notifyDataSetChanged();



            }

            @Override
            public void onFailure(Call<List<Upc>> call, Throwable t) {
                Log.d("IngredientActivity", "onFailure: Call failed.");
            }
        });


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
