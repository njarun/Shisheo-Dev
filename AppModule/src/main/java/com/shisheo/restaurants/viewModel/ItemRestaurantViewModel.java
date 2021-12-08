package com.shisheo.restaurants.viewModel;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.shisheo.restaurants.model.Restaurant;
import com.shisheo.restaurants.view.activity.RestaurantDetailActivity;

/**
 * Created by Arun Jose on 7/12/21.
 */

public class ItemRestaurantViewModel extends BaseObservable {

    private final Context context;
    private Restaurant restaurant;
    private static int imgHeight = -1;

    public ItemRestaurantViewModel(Context context, Restaurant restaurant) {

        this.context = context;
        this.restaurant = restaurant;

        if(imgHeight == -1) {

            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            imgHeight = metrics.widthPixels *7/20;
        }
    }

    public String getImageUrl() {
        return restaurant.image_url;
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {

        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.height = imgHeight;
        imageView.setLayoutParams(lp);
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public String getName() {
        return restaurant.name;
    }

    public String getDescription() { return restaurant.description; }

    public String getOffer() { return restaurant.offer; }

    public String getDeliveryCharge() {
        return restaurant.delivery_charge;
    }

    public int getDeliveryVisibility() {

        if(restaurant.delivery_charge != null && !restaurant.delivery_charge.isEmpty() ) {

            return View.VISIBLE;
        }
        else
            return View.GONE;
    }

    public int getRatingBarVisibility() {

        if(restaurant.rating > 0) {

            return View.VISIBLE; //hardcoded because no param in response
        }
        else
            return View.GONE;
    }

    public float getRating() {

        return restaurant.rating;
    }

    public void onItemClick(View v) {

        context.startActivity(RestaurantDetailActivity.fillDetail(v.getContext(), restaurant));
    }

    public void onImageClick(View v) {

        restaurant.rating = 5;
        notifyChange();
    }

    public void setRestaurant(Restaurant restaurant) {

        this.restaurant = restaurant;
        notifyChange();
    }
}
