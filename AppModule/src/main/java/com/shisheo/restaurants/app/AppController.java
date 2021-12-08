package com.shisheo.restaurants.app;

import android.app.Application;
import android.content.Context;

import com.shisheo.restaurants.network.ApiFactory;
import com.shisheo.restaurants.network.RestaurantService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Arun Jose on 7/12/21.
 */

public class AppController extends Application {

    private RestaurantService restaurantService;
    private Scheduler scheduler;

    private static AppController get(Context context) {
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }

    public RestaurantService getUserService() {

        if (restaurantService == null) {
            restaurantService = ApiFactory.create();
        }

        return restaurantService;
    }

    public Scheduler subscribeScheduler() {

        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setUserService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

}
