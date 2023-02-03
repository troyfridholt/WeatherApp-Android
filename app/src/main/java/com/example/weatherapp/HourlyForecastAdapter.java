package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HourlyForecastAdapter extends ArrayAdapter<HourlyForecast> {

    public HourlyForecastAdapter(Context context, ArrayList<HourlyForecast> hourlyForecasts) {
        super(context, 0, hourlyForecasts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HourlyForecast hourlyForecast = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_hourly_forecast, parent, false);
        }

        TextView temperatureTextView = convertView.findViewById(R.id.temperatureTextView);
        temperatureTextView.setText(String.valueOf(hourlyForecast.getTemperature()));

        TextView humidityTextView = convertView.findViewById(R.id.humidityTextView);
        humidityTextView.setText(String.valueOf(hourlyForecast.getHumidity()));

        TextView weatherDescriptionTextView = convertView.findViewById(R.id.weatherDescriptionTextView);
        weatherDescriptionTextView.setText(hourlyForecast.getWeatherDescription());

        TextView windSpeedTextView = convertView.findViewById(R.id.windSpeedTextView);
        windSpeedTextView.setText(String.valueOf(hourlyForecast.getWindSpeed()));

        TextView dateTextView = convertView.findViewById(R.id.dateTextView);
        dateTextView.setText(hourlyForecast.getDate());

        return convertView;
    }
}
