package com.shisheo.restaurants.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.shisheo.restaurants.R;
import com.shisheo.restaurants.databinding.ActivityRestaurantDetailBinding;
import com.shisheo.restaurants.model.Restaurant;
import com.shisheo.restaurants.viewModel.RestaurantDetailViewModel;

public class RestaurantDetailActivity extends AppCompatActivity {

    private static final String EXTRA_USER = "EXTRA_USER";

    private ActivityRestaurantDetailBinding activityUserDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        activityUserDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_detail);
        setSupportActionBar(activityUserDetailBinding.toolbar);
        displayHomeAsUpEnabled();
        getExtrasFromIntent();
    }

    private void displayHomeAsUpEnabled() {

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static Intent fillDetail(Context context, Restaurant restaurant) {

        Intent intent = new Intent(context, RestaurantDetailActivity.class);
        intent.putExtra(EXTRA_USER, restaurant);
        return intent;
    }

    private void getExtrasFromIntent() {

        Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra(EXTRA_USER);
        RestaurantDetailViewModel restaurantDetailViewModel = new RestaurantDetailViewModel(this, restaurant);
        activityUserDetailBinding.setRestaurantDetailViewModel(restaurantDetailViewModel);
        setTitle(restaurant.name);
    }
}