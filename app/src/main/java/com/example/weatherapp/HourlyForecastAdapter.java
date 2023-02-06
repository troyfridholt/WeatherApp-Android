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
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_hourly_forecast, parent, false);
        }

        HourlyForecast currentHourlyForecast = getItem(position);

        TextView temperatureTextView = listItemView.findViewById(R.id.temperatureTextView);
        temperatureTextView.setText("Temperatur: " + currentHourlyForecast.getTemperature().toString());

        TextView humidityTextView = listItemView.findViewById(R.id.humidityTextView);
        humidityTextView.setText("Luftfuktighet: " + currentHourlyForecast.getHumidity().toString());

        TextView weatherDescriptionTextView = listItemView.findViewById(R.id.weatherDescriptionTextView);
        weatherDescriptionTextView.setText("Väderbeskrivning: " + currentHourlyForecast.getWeatherDescription());

        TextView windSpeedTextView = listItemView.findViewById(R.id.windSpeedTextView);
        windSpeedTextView.setText("Vindhastighet: " + currentHourlyForecast.getWindSpeed().toString());

        TextView dateTimeTextView = listItemView.findViewById(R.id.dateTimeTextView);
        dateTimeTextView.setText("Datum/Tid: " + currentHourlyForecast.getDateTime());

        return listItemView;
    }
}
