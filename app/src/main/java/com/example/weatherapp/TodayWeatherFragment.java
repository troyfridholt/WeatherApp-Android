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

public class TodayWeatherFragment extends Fragment {

    private TodayWeatherAdapter todayWeatherAdapter;
    private ArrayList<TodayWeather> todayWeatherList;
    private final String API_KEY = "67acd4e8f0cfb70fa1d19299bb3f796c";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_today_weather, container, false);

        ListView listView = rootView.findViewById(R.id.todayWeatherListView);

        todayWeatherList = new ArrayList<>();
        todayWeatherAdapter = new TodayWeatherAdapter(getActivity(), todayWeatherList);

        listView.setAdapter(todayWeatherAdapter);

        fetchTodayWeatherData();

        return rootView;
    }

    private void fetchTodayWeatherData() {
        String URL = getString(R.string.weather_api_url, "London,uk", API_KEY);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray list = response.getJSONArray("list");

                            JSONObject jsonObject = list.getJSONObject(0);

                            JSONObject main = jsonObject.getJSONObject("main");
                            Double temperature = main.getDouble("temp");

                            temperature = temperature - 273.15;
                            Double humidity = main.getDouble("humidity");

                            JSONArray weatherArray = jsonObject.getJSONArray("weather");
                            JSONObject weather = weatherArray.getJSONObject(0);
                            String weatherDescription = weather.getString("description");

                            JSONObject wind = jsonObject.getJSONObject("wind");
                            Double windSpeed = wind.getDouble("speed");

                            String dateTime = jsonObject.getString("dt_txt");

                            TodayWeather todayWeather = new TodayWeather(temperature, humidity, weatherDescription, windSpeed, dateTime);
                            todayWeatherList.clear(); // Clear the list before adding the new item
                            todayWeatherList.add(todayWeather);
                            todayWeatherAdapter.notifyDataSetChanged();
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
