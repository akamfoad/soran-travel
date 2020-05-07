package com.fanaye.sorantravel.ui.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.google.gson.Gson;

import java.util.Locale;

import static java.lang.String.format;

public class WeatherFragment extends Fragment {

    private ImageView condIcon;
    private ProgressBar progressBar;
    private TextView conditionDesc;
    private TextView temp;
    private TextView press;
    private TextView humidity;
    private TextView windSpeed;
    private TextView windDeg;
    private byte[] imageData;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.weather_fragment, container, false);
        if (getResources().getConfiguration().getLocales().get(0).getLanguage().equalsIgnoreCase("ku")) {
            root.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        progressBar = root.findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        condIcon = root.findViewById(R.id.cond_icon);
        conditionDesc = root.findViewById(R.id.conditionDesc);
        temp = root.findViewById(R.id.temp);
        press = root.findViewById(R.id.pressure);
        humidity = root.findViewById(R.id.humidity);
        windSpeed = root.findViewById(R.id.wind_speed);
        windDeg = root.findViewById(R.id.wind_deg);

        final String cityID = "92002";
        JSONWeatherTask jsonWeatherTask = new JSONWeatherTask();
        jsonWeatherTask.execute(cityID);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private class JSONWeatherTask extends AsyncTask<String, Integer, DATA> {

        @Override
        protected DATA doInBackground(String... params) {

            publishProgress(10);
            String json = ((new WeatherHttpClient()).getWeatherData(params[0], getString(R.string.WEATHER_API_KEY)));
            publishProgress(30);
            Gson gson = new Gson();
            publishProgress(50);
            DATA data = gson.fromJson(json, DATA.class);
            publishProgress(75);
            imageData = ((new WeatherHttpClient()).getImage(data.getWeather().get(0).getIcon()));
            publishProgress(100);
            return data;
        }

        @Override
        protected void onPostExecute(DATA data) {
            super.onPostExecute(data);
            progressBar.setVisibility(View.GONE);
            if (imageData != null && imageData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                condIcon.setImageBitmap(img);
            }

            conditionDesc.setText(format(Locale.getDefault(), "%s(%s)", data.getWeather().get(0).getMain(), data.getWeather().get(0).getDescription()));
            temp.setText(format(Locale.ENGLISH, "%d°C", Math.round((data.getMain().getTemp()))));
            humidity.setText(String.format(Locale.ENGLISH, "%d%%", Math.round(data.getMain().getHumidity())));
            press.setText(String.format(Locale.ENGLISH, "%d hPa", Math.round(data.getMain().getPressure())));
            windSpeed.setText(String.format("%s mps", data.getWind().getSpeed()));
            windDeg.setText(String.format(Locale.ENGLISH, "%d°", data.getWind().getDeg()));
        }
    }


}
