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

    private ArrayList<HourlyForecast> mHourlyForecastList;
    private HourlyForecastAdapter mHourlyForecastAdapter;
    private final String API_KEY = "67acd4e8f0cfb70fa1d19299bb3f796c";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hourly_forecast, container, false);

        ListView listView = rootView.findViewById(R.id.hourlyForecastListView);

        mHourlyForecastList = new ArrayList<>();
        mHourlyForecastAdapter = new HourlyForecastAdapter(getActivity(), mHourlyForecastList);

        listView.setAdapter(mHourlyForecastAdapter);

        fetchHourlyForecastData();

        return rootView;
    }

    private void fetchHourlyForecastData() {
        String URL = "https://api.openweathermap.org/data/2.5/forecast?q=London,uk&appid=" + API_KEY;

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray list = response.getJSONArray("list");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject jsonObject = list.getJSONObject(i);

                        JSONObject main = jsonObject.getJSONObject("main");
                        Double temperature = main.getDouble("temp");
                        Double humidity = main.getDouble("humidity");

                        JSONArray weatherArray = jsonObject.getJSONArray("weather");
                        JSONObject weather = weatherArray.getJSONObject(0);
                        String weatherDescription = weather.getString("description");

                        JSONObject wind = jsonObject.getJSONObject("wind");
                        Double windSpeed = wind.getDouble("speed");

                        String date = jsonObject.getString("dt_txt");
                        HourlyForecast hourlyForecast = new HourlyForecast(temperature, humidity, weatherDescription, windSpeed, date);
                        mHourlyForecastList.add(hourlyForecast);
                    }

                    mHourlyForecastAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}