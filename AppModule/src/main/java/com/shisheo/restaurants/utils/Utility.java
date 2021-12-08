package com.shisheo.restaurants.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Utility {

    public static int convertDpToPixel(Context context, float dp) {

        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = (int) (dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
