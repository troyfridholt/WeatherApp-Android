package com.example.weatherapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WeeklyForecastAdapter extends ArrayAdapter<WeeklyForecast> {

    public WeeklyForecastAdapter(Context context, ArrayList<WeeklyForecast> weeklyForecasts) {
        super(context, 0, weeklyForecasts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_weekly_forecast, parent, false);
        }

        WeeklyForecast currentWeeklyForecast = getItem(position);

        TextView temperatureTextView = listItemView.findViewById(R.id.temperatureTextView);
        temperatureTextView.setText("Temperatur: " + currentWeeklyForecast.getTemperature().toString());

        TextView humidityTextView = listItemView.findViewById(R.id.humidityTextView);
        humidityTextView.setText("Luftfuktighet: " + currentWeeklyForecast.getHumidity().toString());

        TextView weatherDescriptionTextView = listItemView.findViewById(R.id.weatherDescriptionTextView);
        weatherDescriptionTextView.setText("Väderbeskrivning: " + currentWeeklyForecast.getWeatherDescription());

        TextView windSpeedTextView = listItemView.findViewById(R.id.windSpeedTextView);
        windSpeedTextView.setText("Vindhastighet: " + currentWeeklyForecast.getWindSpeed().toString());

        TextView dateTimeTextView = listItemView.findViewById(R.id.dateTimeTextView);
        dateTimeTextView.setText("Datum/Tid: " + currentWeeklyForecast.getDateTime());

        return listItemView;
    }
}
