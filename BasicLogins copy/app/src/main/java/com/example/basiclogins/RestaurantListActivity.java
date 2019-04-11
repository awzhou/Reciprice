package com.example.basiclogins;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {

    private ListView listViewRestaurant;
    private FloatingActionButton floatingActionButtonAdd;
    public static final String EXTRA_RESTAURANT = "the restaurant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        wireWidgets();
        populateListView();
        setOnClickListeners();

        registerForContextMenu(listViewRestaurant);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.restaurant_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        switch (item.getItemId()) {
            case R.id.delete_restaurant:
                Restaurant restaurant = (Restaurant) listViewRestaurant.getItemAtPosition(index);
                deleteRestaurant(restaurant);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.log_out:
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logOut() {
        Backendless.UserService.logout( new AsyncCallback<Void>()
        {
            public void handleResponse( Void response )
            {
                // user has been logged out.
                Toast.makeText(RestaurantListActivity.this, "User Logged Out Successfully", Toast.LENGTH_SHORT).show();
                Intent logOutIntent = new Intent(RestaurantListActivity.this, LoginActivity.class);
                startActivity(logOutIntent);
            }

            public void handleFault( BackendlessFault fault )
            {
                // something went wrong and logout failed, to get the error code call fault.getCode()
                Toast.makeText(RestaurantListActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void deleteRestaurant(Restaurant restaurant) {
        Backendless.Persistence.of( Restaurant.class ).remove(restaurant,
            new AsyncCallback<Long>()
            {
                public void handleResponse( Long response )
                {
                    // Contact has been deleted. The response is the
                    // time in milliseconds when the object was deleted
                    populateListView();
                    Toast.makeText(RestaurantListActivity.this, "Restaurant successfully deleted", Toast.LENGTH_SHORT).show();
                }
                public void handleFault( BackendlessFault fault )
                {
                    // an error has occurred, the error code can be
                    // retrieved with fault.getCode()
                }
            } );
    }


    private void setOnClickListeners() {
        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantListActivity.this, RestaurantActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        populateListView();
    }

    private void populateListView() {
        //refactor to only get the items that belong to the user
        //get the current user's objectId (hint: use Backendless.UserService
        //make a dataquery and use the advanced object retrieval pattern
        //to find all restaurants whose ownerId matches the user's objectId
        //sample WHERE clause with a string: name = 'Joe'

        String ownerId = Backendless.UserService.CurrentUser().getObjectId();
        String whereClause = "ownerId = '" + ownerId + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause( whereClause );

        Backendless.Data.of( Restaurant.class ).find( queryBuilder,
                new AsyncCallback<List<Restaurant>>(){
                    @Override
                    public void handleResponse(final List<Restaurant> restaurantList )
                    {
                        RestaurantAdapter adapter = new RestaurantAdapter(RestaurantListActivity.this, R.layout.item_restaurantlist, restaurantList);
                        listViewRestaurant.setAdapter(adapter);
                        //set the onItemClickListener to open the Restaurant Activity
                        //take the clicked object and include it in the Intent
                        listViewRestaurant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent restaurantDetailIntent = new Intent(RestaurantListActivity.this, RestaurantActivity.class);
                                restaurantDetailIntent.putExtra(EXTRA_RESTAURANT, restaurantList.get(position));
                                startActivity(restaurantDetailIntent);
                            }
                        });

                        // the "foundRestaurant" collection now contains instances of the Restaurant class.
                        // each instance represents an object stored on the server.
                        Toast.makeText(RestaurantListActivity.this, "Data Successfully Loaded", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void handleFault( BackendlessFault fault )
                    {
                        // an error has occurred, the error code can be retrieved with fault.getCode()
                        Toast.makeText(RestaurantListActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void wireWidgets() {
        listViewRestaurant = findViewById(R.id.listview_restuarantlist);
        floatingActionButtonAdd = findViewById(R.id.floatingActionButton_restaurantList_add);
    }
}
