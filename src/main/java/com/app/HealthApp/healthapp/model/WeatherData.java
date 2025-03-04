package com.app.HealthApp.healthapp.model;



public class WeatherData {

    private CurrentWeather current;

    public CurrentWeather getCurrent() {
        return current;
    }
//Koristi se nested klasa jer JSON ima ugniježđenu strukturu (current)
    public void setCurrent(CurrentWeather current){
        this.current = current;
    }

    public static class CurrentWeather{
        private double temperature_2m;
        private double precipitation;
        private double wind_speed_10m;

        public double getTemperature_2m() {
            return temperature_2m;
        }

        public void setTemperature_2m(double temperature_2m) {
            this.temperature_2m = temperature_2m;
        }

        public double getPrecipitation() {
            return precipitation;
        }

        public void setPrecipitation(double precipitation) {
            this.precipitation = precipitation;
        }

        public double getWind_speed_10m() {
            return wind_speed_10m;
        }

        public void setWind_speed_10m(double wind_speed_10m) {
            this.wind_speed_10m = wind_speed_10m;
        }
    }

}
