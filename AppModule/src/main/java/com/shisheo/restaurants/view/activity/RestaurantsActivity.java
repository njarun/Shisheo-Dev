package com.shisheo.restaurants.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shisheo.restaurants.R;
import com.shisheo.restaurants.databinding.ActivityRestaurantsBinding;
import com.shisheo.restaurants.view.adapter.RestaurantAdapter;
import com.shisheo.restaurants.viewModel.RestaurantViewModel;

import java.util.Observable;
import java.util.Observer;

public class RestaurantsActivity extends AppCompatActivity implements Observer {

    private ActivityRestaurantsBinding restaurantsBinding;
    private RestaurantViewModel restaurantViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initDataBinding();

        //setSupportActionBar(restaurantsBinding.toolbar);//removed because no action bar in design

        setUpListOfRestaurantView(restaurantsBinding.listRestaurants);

        setUpObserver(restaurantViewModel);

        initializeAndFetch();
    }

    private void initDataBinding() {

        restaurantsBinding = DataBindingUtil.setContentView(this, R.layout.activity_restaurants);
        restaurantViewModel = new RestaurantViewModel(this);
        restaurantsBinding.setRestaurantViewModel(restaurantViewModel);
    }

    private void initializeAndFetch() {

        restaurantViewModel.initialize();
    }

    // set up the list of user with recycler view
    private void setUpListOfRestaurantView(RecyclerView listRestaurants) {

        RestaurantAdapter restaurantAdapter = new RestaurantAdapter();
        listRestaurants.setAdapter(restaurantAdapter);
        listRestaurants.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

        if(o instanceof RestaurantViewModel) {

            RestaurantAdapter restaurantAdapter = (RestaurantAdapter) restaurantsBinding.listRestaurants.getAdapter();

            if(restaurantAdapter != null) {

                RestaurantViewModel restaurantViewModel = (RestaurantViewModel) o;
                restaurantAdapter.setRestaurantList(restaurantViewModel.getRestaurantList());
            }
        }
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        restaurantViewModel.reset();
    }
}
