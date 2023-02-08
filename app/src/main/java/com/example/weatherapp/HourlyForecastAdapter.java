package com.example.weatherapp;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class HourlyForecastAdapter extends ArrayAdapter<HourlyForecast> {
    private Context context;

    public HourlyForecastAdapter(Context context, ArrayList<HourlyForecast> hourlyForecasts) {
        super(context, 0, hourlyForecasts);
        this.context = context;
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

        Button backButton = listItemView.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

        return listItemView;
    }
}
