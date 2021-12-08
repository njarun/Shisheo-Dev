package com.shisheo.restaurants.network;

import static com.shisheo.restaurants.utils.Constant.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Arun Jose on 7/12/21.
 */

public class ApiFactory {

    public static RestaurantService create() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(RestaurantService.class);
    }
}
