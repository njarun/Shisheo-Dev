package com.shisheo.restaurants.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.shisheo.restaurants.R;
import com.shisheo.restaurants.databinding.ItemRestaurantBinding;
import com.shisheo.restaurants.model.Restaurant;
import com.shisheo.restaurants.viewModel.ItemRestaurantViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Arun Jose on 7/12/21.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurantList;

    public RestaurantAdapter() {this.restaurantList = Collections.emptyList();}

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemRestaurantBinding itemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_restaurant,parent, false);
        return new RestaurantViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.bindUser(restaurantList.get(position));
    }

    @Override
    public int getItemCount() {
        return  restaurantList.size();
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
        notifyDataSetChanged();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        ItemRestaurantBinding mRestaurantBinding;

        public RestaurantViewHolder(ItemRestaurantBinding itemRestaurantBinding) {

            super(itemRestaurantBinding.itemRestaurant);
            this.mRestaurantBinding = itemRestaurantBinding;
        }

        void bindUser(Restaurant restaurant) {

            if(mRestaurantBinding.getRestaurantViewModel() == null){

                mRestaurantBinding.setRestaurantViewModel(new ItemRestaurantViewModel(itemView.getContext(), restaurant));
            }
            else {
                mRestaurantBinding.getRestaurantViewModel().setRestaurant(restaurant);
            }
        }
    }
}
