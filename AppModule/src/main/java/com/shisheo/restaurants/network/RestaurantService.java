package com.shisheo.restaurants.network;

import com.shisheo.restaurants.model.Restaurant;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Arun Jose on 7/12/21.
 */

public interface RestaurantService {

    @GET
    Observable<List<Restaurant>> fetchRestaurants(@Url String url);
}
