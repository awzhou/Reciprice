package com.example.reciprice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.reciprice.R;
import com.example.reciprice.model.Product;
import com.example.reciprice.model.Upc;
import com.example.reciprice.repo.IngredientsJson;
import com.example.reciprice.repo.UpcService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class IngredientActivity extends AppCompatActivity {//implements UpcAdapter.ItemClickListener
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

        //upcAdapter.setClickListener(this);
        recyclerViewUpcs.setAdapter(upcAdapter);
        recyclerViewUpcs.setLayoutManager(layoutManager);

        ingredient = getIntent().getStringExtra("Ingredient");

        //create json to pass into POST body
        IngredientsJson json = new IngredientsJson(ingredient);

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
                upcs.addAll(newUpcs);
                upcAdapter.notifyDataSetChanged();



            }

            @Override
            public void onFailure(Call<List<Upc>> call, Throwable t) {
                Log.d("IngredientActivity", "onFailure: Call failed.");
            }
        });


    }

//    @Override
//    public void onItemClick(View view, int position) {
//        Toast.makeText(this, "You clicked " + upcAdapter.getItem(position), Toast.LENGTH_SHORT).show();
//
//        Intent intent = new Intent(IngredientActivity.this, PriceActivity.class);
//        intent.putExtra("Ingredient", upcAdapter.getItem(position));
//        startActivity(intent);
//        //TODO: search this clicked ingredient in the grocery store service
//    }

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
