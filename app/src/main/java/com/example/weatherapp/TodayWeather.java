package com.example.weatherapp;



public class TodayWeather {


    private Double temperature;
    private Double humidity;
    private String weatherDescription;
    private Double windSpeed;
    private String dateTime;

    public TodayWeather(Double temperature, Double humidity, String weatherDescription, Double windSpeed, String dateTime) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.weatherDescription = weatherDescription;
        this.windSpeed = windSpeed;
        this.dateTime = dateTime;

    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public String getDateTime() {
        return dateTime;
    }
}
