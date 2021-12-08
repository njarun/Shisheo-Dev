package com.shisheo.restaurants.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Arun Jose on 7/12/21.
 */

public class Restaurant implements Serializable {

    @SerializedName("image_url") public String image_url;

    @SerializedName("name") public String name;

    @SerializedName("description") public String description;

    @SerializedName("offer") public String offer;

    @SerializedName("delivery_charge") public String delivery_charge;

    @SerializedName("rating") public float rating;
}
