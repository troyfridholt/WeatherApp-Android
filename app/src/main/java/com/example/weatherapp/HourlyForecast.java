package com.example.weatherapp;

public class HourlyForecast {

    private Double mTemperature;
    private Double mHumidity;
    private String mWeatherDescription;
    private Double mWindSpeed;
    private String mDate;

    public HourlyForecast(Double temperature, Double humidity, String weatherDescription, Double windSpeed, String date) {
        mTemperature = temperature;
        mHumidity = humidity;
        mWeatherDescription = weatherDescription;
        mWindSpeed = windSpeed;
        mDate = date;
    }

    public Double getTemperature() {
        return mTemperature;
    }

    public Double getHumidity() {
        return mHumidity;
    }

    public String getWeatherDescription() {
        return mWeatherDescription;
    }

    public Double getWindSpeed() {
        return mWindSpeed;
    }

    public String getDate() {
        return mDate;
    }
}
