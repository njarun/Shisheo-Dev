package com.shisheo.restaurants.viewModel;

import static com.shisheo.restaurants.utils.Constant.RANDOM_RESTAURANT_URL;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.shisheo.restaurants.R;
import com.shisheo.restaurants.app.AppController;
import com.shisheo.restaurants.model.Restaurant;
import com.shisheo.restaurants.network.RestaurantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Arun Jose on 7/12/21.
 */

public class RestaurantViewModel extends Observable {

    public ObservableInt progressBar;
    public ObservableInt userRecycler;
    public ObservableInt restaurantLabel;
    public ObservableField<String> messageLabel;

    private final List<Restaurant> restaurantList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RestaurantViewModel(@NonNull Context context) {

        this.context = context;
        this.restaurantList = new ArrayList<>();
        progressBar = new ObservableInt(View.GONE);
        userRecycler = new ObservableInt(View.GONE);
        restaurantLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.default_message_loading));
    }

    public void initialize() {

        initializeViews();
        fetchRestaurantList();
    }

    private void initializeViews() {

        restaurantLabel.set(View.GONE);
        userRecycler.set(View.GONE);
        progressBar.set(View.VISIBLE);
    }

    private void fetchRestaurantList() {

        AppController appController = AppController.create(context);
        RestaurantService restaurantService = appController.getUserService();

        Disposable disposable = restaurantService.fetchRestaurants(RANDOM_RESTAURANT_URL)
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Restaurant>>() {

                    @Override
                    public void accept(List<Restaurant> restaurant_list) throws Exception {

                        updateDeliveryChargeTemp(restaurant_list); //Temp method to update delivery charges, since its not in the response

                        updateUserDataList(restaurant_list);

                        progressBar.set(View.GONE);
                        restaurantLabel.set(View.GONE);
                        userRecycler.set(View.VISIBLE);
                    }
                },
                new Consumer<Throwable>() {

                    @Override public void accept(Throwable throwable) throws Exception {

                        messageLabel.set(context.getString(R.string.error_message_loading_restaurants));
                        progressBar.set(View.GONE);
                        restaurantLabel.set(View.VISIBLE);
                        userRecycler.set(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void updateDeliveryChargeTemp(List<Restaurant> restaurant_list) {

        for (Restaurant restaurant: restaurant_list) {

            if(restaurant.name != null &&
                    restaurant.name.equalsIgnoreCase("Delicio Cuisine Place")) { //hardcoded because no param in response

                restaurant.delivery_charge = "Delivery: AED 10";
                break;
            }
        }
    }

    private void updateUserDataList(List<Restaurant> restaurants) {

        restaurantList.addAll(restaurants);
        setChanged();
        notifyObservers();
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    private void unSubscribeFromObservable() {

        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {

        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
}

