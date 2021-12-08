package com.shisheo.restaurants.viewModel;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.shisheo.restaurants.model.Restaurant;

import java.util.Observable;

/**
 * Created by Arun Jose on 7/12/21.
 */
/*
** This Class as ViewModel to exposes streams of data relevant to the View (UserDetailActivity).
 */

public class RestaurantDetailViewModel extends Observable {

    private final Context mContext;
    private final Restaurant restaurant;
    private static int imgHeight = -1;

    public RestaurantDetailViewModel(Context context, Restaurant restaurant) {

        this.mContext = context;
        this.restaurant = restaurant;

        if(imgHeight == -1) {

            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            imgHeight = metrics.widthPixels *3/4;
        }
    }

    public String getName() {
        return restaurant.name;
    }

    public String getOffer() { return restaurant.offer; }

    public String getDescription() { return restaurant.description; }

    public String getImageUrlDetail() { return restaurant.image_url; }

    @BindingAdapter({"imageUrlDetail"})
    public static void setImageUrlDetail(ImageView imageView, String imageUrl) {

        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.height = imgHeight;
        imageView.setLayoutParams(lp);
        Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
    }
}
