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

public class TodayWeatherAdapter extends ArrayAdapter<TodayWeather> {
    private Context context;
    public TodayWeatherAdapter(Context context, ArrayList<TodayWeather> todayWeathers) {
        super(context, 0, todayWeathers);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_today_weather, parent, false);
        }

        TodayWeather currentTodayWeather = getItem(position);

        TextView temperatureTextView = listItemView.findViewById(R.id.temperatureTextView);
        temperatureTextView.setText("Temperatur: " + currentTodayWeather.getTemperature().toString());

        TextView humidityTextView = listItemView.findViewById(R.id.humidityTextView);
        humidityTextView.setText("Luftfuktighet: " + currentTodayWeather.getHumidity().toString());

        TextView weatherDescriptionTextView = listItemView.findViewById(R.id.weatherDescriptionTextView);
        weatherDescriptionTextView.setText("Väderbeskrivning: " + currentTodayWeather.getWeatherDescription());

        TextView windSpeedTextView = listItemView.findViewById(R.id.windSpeedTextView);
        windSpeedTextView.setText("Vindhastighet: " + currentTodayWeather.getWindSpeed().toString());

        TextView dateTimeTextView = listItemView.findViewById(R.id.dateTimeTextView);
        dateTimeTextView.setText("Datum/Tid: " + currentTodayWeather.getDateTime());
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
