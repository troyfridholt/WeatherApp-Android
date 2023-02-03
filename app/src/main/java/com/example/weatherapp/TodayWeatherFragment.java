package com.example.weatherapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class TodayWeatherFragment extends Fragment {

    private TextView temperatureTextView;
    private TextView humidityTextView;
    private TextView windSpeedTextView;
    private TextView pressureTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_today_weather, container, false);

        temperatureTextView = rootView.findViewById(R.id.temperatureTextView);
        humidityTextView = rootView.findViewById(R.id.humidityTextView);
        windSpeedTextView = rootView.findViewById(R.id.windSpeedTextView);
        pressureTextView = rootView.findViewById(R.id.pressureTextView);
        fetchWeatherData();

        return rootView;
    }

    private void fetchWeatherData() {
        String API_KEY = "67acd4e8f0cfb70fa1d19299bb3f796c";
        String URL = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=" + API_KEY;

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject main = response.getJSONObject("main");
                            Double temperature = main.getDouble("temp");
                            Double humidity = main.getDouble("humidity");
                            Double pressure = main.getDouble("pressure");

                            JSONObject wind = response.getJSONObject("wind");
                            Double windSpeed = wind.getDouble("speed");

                            temperatureTextView.setText(String.valueOf(temperature));
                            humidityTextView.setText(String.valueOf(humidity));
                            windSpeedTextView.setText(String.valueOf(windSpeed));
                            pressureTextView.setText(String.valueOf(pressure));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error here
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
}
