package com.app.HealthApp.healthapp.service;


import com.app.HealthApp.healthapp.model.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    //Statična varijabla koja pripada klasi (nije potrebno instancirati za pristup)
    private static final String API_URL = "https://api.open-meteo.com/v1/forecast?latitude=44.8683&longitude=13.8481&current=temperature_2m,precipitation,wind_speed_10m&forecast_days=3";

    //Fetcha podatke iz url-a i mapira ga u WeatherData objekt. Returna objekt WeatherData klase
    public WeatherData getWeatherData(){
        //Spring klasa koja šalje HTTP request RESTful web servisima.
        RestTemplate restTemplate = new RestTemplate();
        //Šalje GET request URL-u, mapira odgovor u objekt tipa WeatherData klase
        return restTemplate.getForObject(API_URL, WeatherData.class);
    }

}
