package com.fanaye.sorantravel.ui.weather;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fanaye.sorantravel.R;
import com.fanaye.sorantravel.ui.weather.model.DATA;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;

import java.util.Locale;

import static androidx.core.content.ContextCompat.getSystemService;
import static java.lang.String.format;

public class WeatherFragment extends Fragment {
    private final String cityID = "92002";

    private ImageView condIcon;
    JSONWeatherTask jsonWeatherTask;
    private ProgressBar progressBar;
    private TextView conditionDesc;
    private TextView temp;
    private TextView press;
    private TextView internetErrorLabel;
    private TextView humidity;
    private TextView pressLabel;
    private TextView windSpeed;
    private TextView windDeg;
    private TextView humidityLabel;
    private byte[] imageData;
    private TextView windLabel;
    private FirebaseAnalytics crashAnalysis;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.weather_fragment, container, false);
        crashAnalysis = FirebaseAnalytics.getInstance(getContext());
        if (getResources().getConfiguration().getLocales().get(0).getLanguage().equalsIgnoreCase("ku")) {
            root.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        internetErrorLabel = root.findViewById(R.id.internet_error_label);
        progressBar = root.findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        condIcon = root.findViewById(R.id.cond_icon);
        conditionDesc = root.findViewById(R.id.conditionDesc);
        temp = root.findViewById(R.id.temp);
        press = root.findViewById(R.id.pressure);
        pressLabel = root.findViewById(R.id.pressure_label);
        humidity = root.findViewById(R.id.humidity);
        humidityLabel = root.findViewById(R.id.humidity_label);
        windSpeed = root.findViewById(R.id.wind_speed);
        windDeg = root.findViewById(R.id.wind_deg);
        windLabel = root.findViewById(R.id.wind_label);

        if (internetAvailable(getContext())) {
            jsonWeatherTask = new JSONWeatherTask();
            jsonWeatherTask.execute(cityID);
        } else {
            condIcon.setImageResource(R.drawable.ic_signal_wifi_off_black_24dp);
            conditionDesc.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            temp.setVisibility(View.GONE);
            press.setVisibility(View.GONE);
            pressLabel.setVisibility(View.GONE);
            humidityLabel.setVisibility(View.GONE);
            windLabel.setVisibility(View.GONE);
            humidity.setVisibility(View.GONE);
            windSpeed.setVisibility(View.GONE);
            windDeg.setVisibility(View.GONE);
            internetErrorLabel.setVisibility(View.VISIBLE);
        }

        root.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            jsonWeatherTask = new JSONWeatherTask();
            jsonWeatherTask.execute(cityID);
        });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private boolean internetAvailable(Context c) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(c, ConnectivityManager.class);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    private class JSONWeatherTask extends AsyncTask<String, Integer, DATA> {

        @Override
        protected DATA doInBackground(String... params) {
            if (!internetAvailable(getContext())) {
                cancel(false);
            }
            publishProgress(10);
            String json = ((new WeatherHttpClient(crashAnalysis)).getWeatherData(params[0], getString(R.string.WEATHER_API_KEY)));

            publishProgress(30);
            Gson gson = new Gson();
            publishProgress(50);
            DATA data = null;
            if (json != null) {
                data = gson.fromJson(json, DATA.class);
            }
            publishProgress(75);
            if (data != null) {
                imageData = ((new WeatherHttpClient(crashAnalysis)).getImage(data.getWeather().get(0).getIcon()));
            }
            publishProgress(100);
            return data;
        }

        @Override
        protected void onCancelled(DATA data) {
            condIcon.setImageResource(R.drawable.ic_signal_wifi_off_black_24dp);
            conditionDesc.setVisibility(View.GONE);
            conditionDesc.setText(R.string.NO_INTERNET_CONNECTION);
            progressBar.setVisibility(View.GONE);
            temp.setVisibility(View.GONE);
            press.setVisibility(View.GONE);
            pressLabel.setVisibility(View.GONE);
            humidityLabel.setVisibility(View.GONE);
            windLabel.setVisibility(View.GONE);
            humidity.setVisibility(View.GONE);
            windSpeed.setVisibility(View.GONE);
            windDeg.setVisibility(View.GONE);
            internetErrorLabel.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(DATA data) {
            super.onPostExecute(data);
            progressBar.setVisibility(View.GONE);
            if (imageData != null && imageData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                condIcon.setImageBitmap(img);
            }
            if (data != null) {
                internetErrorLabel.setVisibility(View.GONE);
                conditionDesc.setVisibility(View.VISIBLE);
                temp.setVisibility(View.VISIBLE);
                press.setVisibility(View.VISIBLE);
                pressLabel.setVisibility(View.VISIBLE);
                humidityLabel.setVisibility(View.VISIBLE);
                windLabel.setVisibility(View.VISIBLE);
                humidity.setVisibility(View.VISIBLE);
                windSpeed.setVisibility(View.VISIBLE);
                windDeg.setVisibility(View.VISIBLE);

                conditionDesc.setText(format(Locale.getDefault(), "%s(%s)", data.getWeather().get(0).getMain(), data.getWeather().get(0).getDescription()));
                temp.setText(format(Locale.ENGLISH, "%d°C", Math.round((data.getMain().getTemp()))));
                humidity.setText(String.format(Locale.ENGLISH, "%d%%", Math.round(data.getMain().getHumidity())));
                press.setText(String.format(Locale.ENGLISH, "%d hPa", Math.round(data.getMain().getPressure())));
                windSpeed.setText(String.format("%s mps", data.getWind().getSpeed()));
                windDeg.setText(String.format(Locale.ENGLISH, "%d°", data.getWind().getDeg()));
            }
        }
    }

}
