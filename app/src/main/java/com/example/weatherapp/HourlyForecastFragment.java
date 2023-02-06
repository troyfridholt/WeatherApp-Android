package com.example.weatherapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HourlyForecastFragment extends Fragment {

    private HourlyForecastAdapter hourlyForecastAdapter;
    private ArrayList<HourlyForecast> hourlyForecastList;
    private final String API_KEY = "67acd4e8f0cfb70fa1d19299bb3f796c";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hourly_forecast, container, false);

        ListView listView = rootView.findViewById(R.id.hourlyForecastListView);

        hourlyForecastList = new ArrayList<>();
        hourlyForecastAdapter = new HourlyForecastAdapter(getActivity(), hourlyForecastList);

        listView.setAdapter(hourlyForecastAdapter);

        fetchHourlyForecastData();

        return rootView;
    }

    private void fetchHourlyForecastData() {
        String URL = getString(R.string.weather_api_url, "London,uk", API_KEY);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray list = response.getJSONArray("list");

                            for (int i = 0; i < list.length() && i < 6; i++) {
                                JSONObject jsonObject = list.getJSONObject(i);

                                JSONObject main = jsonObject.getJSONObject("main");
                                Double temperature = main.getDouble("temp");
                                //Convert temperature from kelvin to celsius
                                temperature = temperature - 273.15;
                                Double humidity = main.getDouble("humidity");

                                JSONArray weatherArray = jsonObject.getJSONArray("weather");
                                JSONObject weather = weatherArray.getJSONObject(0);
                                String weatherDescription = weather.getString("description");

                                JSONObject wind = jsonObject.getJSONObject("wind");
                                Double windSpeed = wind.getDouble("speed");
                                String dateTime = jsonObject.getString("dt_txt");
                                hourlyForecastList.add(new HourlyForecast(temperature, humidity, weatherDescription, windSpeed, dateTime));
                            }

                            hourlyForecastAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
}