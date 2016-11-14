package com.example.franks.tempcalc;

import android.app.Application;
/**
 * Created by franks on 13/11/16.
 */
public class TempCalcApp extends Application {
    private final static String ABOUT_URL = "http://www.google.com";

    public String getAboutUrl() {
        return ABOUT_URL;
    }
}
