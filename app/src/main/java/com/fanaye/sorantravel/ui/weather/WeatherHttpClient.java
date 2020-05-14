package com.fanaye.sorantravel.ui.weather;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class WeatherHttpClient {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?id=";
    private static String IMG_URL = "http://openweathermap.org/img/wn/";
    private FirebaseAnalytics crashAnalysis;

    WeatherHttpClient(FirebaseAnalytics crashAnalysis) {
        this.crashAnalysis = crashAnalysis;
    }

    public String getWeatherData(String location, String key) {
        HttpURLConnection con = null;
        InputStream is = null;
        String lang = Locale.getDefault().getLanguage();
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ku")) {
            lang = "ar";
        }
        try {
            con = (HttpURLConnection) (new URL(BASE_URL + location + "&APPID=" + key + "&units=metric&lang=" + lang)).openConnection();
            try {
                con.connect();
            } catch (IOException e) {
                Bundle WAPIREQERR_DATA = new Bundle();
                WAPIREQERR_DATA.putString("Message", e.getMessage());
                System.out.println(e.getMessage());
                crashAnalysis.logEvent("Weather_API_req_error", WAPIREQERR_DATA);
            }

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\n");
            }
            is.close();
            con.disconnect();
            return buffer.toString();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
            }
        }

        return null;

    }

    public byte[] getImage(String code) {
        HttpURLConnection con = null;
        InputStream is = null;
        try {
            con = (HttpURLConnection) (new URL(IMG_URL + code + "@2x.png")).openConnection();
            con.connect();
            // Let's read the response
            is = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while (is.read(buffer) != -1)
                baos.write(buffer);

            return baos.toByteArray();
        } catch (IOException e) {
            Bundle WAPIREQERR_DATA = new Bundle();
            WAPIREQERR_DATA.putString("Message", e.getMessage());
            System.out.println(e.getMessage());
            crashAnalysis.logEvent("Weather_IMAGE_req_error", WAPIREQERR_DATA);
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
            }
        }

        return null;

    }
}
