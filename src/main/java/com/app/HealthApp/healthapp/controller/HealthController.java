package com.app.HealthApp.healthapp.controller;

import com.app.HealthApp.healthapp.model.UserInput;
import com.app.HealthApp.healthapp.model.WeatherData;
import com.app.HealthApp.healthapp.service.HealthService;
import com.app.HealthApp.healthapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HealthController {

    @Autowired
    private HealthService healthService;

    @Autowired
    private WeatherService weatherService;


    @GetMapping("/")
    public String indexPage(Model model){

        // Dohvaća weather podatke i stavlja ih u weatherData objekt
        WeatherData weatherData = weatherService.getWeatherData();

        // Provjeri ako je weatherData null
        if (weatherData != null && weatherData.getCurrent() != null) {
            model.addAttribute("weather", weatherData.getCurrent());
        } else {
            // Hendlanje na index.html
            model.addAttribute("weather", null);
        }
        model.addAttribute("bmi", null);
        model.addAttribute("bodyFatPercentage", null);
        model.addAttribute("ffmi", null);



        return "index";
    }

    @PostMapping("/")
    public String measurmentsPost(
            @RequestParam Integer visina,
            @RequestParam Integer tezina,
            @RequestParam Integer struk,
            @RequestParam Integer godine,
            @RequestParam String spol,
            Model model) {
        System.out.println(visina);
        System.out.println(tezina);
        System.out.println(struk);
        System.out.println(godine);
        System.out.println(spol);

        //Instanciranje objekta
        UserInput userInput = new UserInput();
        userInput.setHeight(visina);
        userInput.setWeight(tezina);
        userInput.setWaist(struk);
        userInput.setAge(godine);
        userInput.setSex(spol);

        //Kalkulacija parametara
        double bmi = healthService.calculateBMI(userInput);
        double bodyFatPercentage = healthService.calculateBodyFat(userInput);
        double ffmi = healthService.calculateFFMI(userInput);
        double bmr = healthService.calculateBMR(userInput);
        double protein = healthService.calculateProtein(userInput);

        //Sa jednom decimalom
        double roundedBMI = Math.round(bmi * 10) / 10.0;
        double roundedBodyFatPercentage = Math.round(bodyFatPercentage * 10) / 10.0;
        double roundedFFMI = Math.round(ffmi * 10) / 10.0;
        double roundedBMR = Math.round(bmr * 10) / 10.0;
        double roundedProtein = Math.round(protein * 10) / 10.0;

        //Konstrukcija poruke 1
        String message_1 = "Tvoj postotak tjelesne masti je ";
        if ("MUSKO".equals(spol)){
            if (bodyFatPercentage < 12){
                message_1 += "vrlo nizak";
            }else if(bodyFatPercentage < 23) {
                message_1 += "unutar zdravog raspona";
            }else {
                message_1 += "iznad prosjeka";
            }
        } else {
            if (bodyFatPercentage < 23){
                message_1 += "vrlo nizak";
            }else if(bodyFatPercentage < 33) {
                message_1 += "unutar zdravog raspona";
            }else {
                message_1 += "iznad prosjeka";
            }
        }
        message_1 += " i tvoja razina mišićne mase je ";
        if ("MUSKO".equals(spol)) {
            if (ffmi > 21) {
                message_1 += "izvrsna.";
            } else if (ffmi > 18) {
                message_1 += "iznad prosjeka.";
            } else {
                message_1 += "ispod prosjeka.";
            }
        } else {
            if (ffmi > 19) {
                message_1 += "izvrsna.";
            } else if (ffmi > 16) {
                message_1 += "iznad prosjeka.";
            } else {
                message_1 += "ispod prosjeka.";
            }
        }

        //Konstrukcija poruke 2
        String message_2 = "Preporuka na temelju rezultata: ";
        if ("MUSKO".equals(spol)){
            if (ffmi > 18 && bodyFatPercentage > 23){
                message_2 += "smanjite težinu kroz dijetu ili fizičku aktivnost.";
            }else if(ffmi > 18 && bodyFatPercentage < 12) {
                message_2 += "povećajte težinu kroz zdravu i raznovrsnu prehranu.";
            }else if(ffmi < 18 && bodyFatPercentage > 23){
                message_2 += "povećajte razinu mišićne mase kroz fizičku aktivnost i trening.";
            }else if(ffmi < 18 && bodyFatPercentage < 12){
                message_2 += "povećajte težinu kroz zdravu i raznovrsnu prehranu i povećajte razinu mišićne mase kroz fizičku aktivnost i trening.";
            } else {
                message_2 += "nema preporuke. Vaši parametri su unutar optimalnog raspona";
            }
        } else {
            if (ffmi > 16 && bodyFatPercentage > 33){
                message_2 += "smanjite težinu kroz dijetu ili fizičku aktivnost.";
            }else if(ffmi > 16 && bodyFatPercentage < 23) {
                message_2 += "povećajte težinu kroz zdravu i raznovrsnu prehranu.";
            }else if(ffmi < 16 && bodyFatPercentage > 33){
                message_2 += "povećajte razinu mišićne mase kroz fizičku aktivnost i trening.";
            }else if(ffmi < 16 && bodyFatPercentage < 23){
                message_2 += "povećajte težinu kroz zdravu i raznovrsnu prehranu i povećajte razinu mišićne mase kroz fizičku aktivnost i trening.";
            } else {
                message_2 += "nema preporuke. Vaši parametri su unutar optimalnog raspona";
            }
        }

        //Sa jednom decimalom
        model.addAttribute("bmi", roundedBMI);
        model.addAttribute("bodyFatPercentage", roundedBodyFatPercentage);
        model.addAttribute("ffmi", roundedFFMI);
        model.addAttribute("bmr", roundedBMR);
        model.addAttribute("protein", roundedProtein);
        model.addAttribute("message_1", message_1);
        model.addAttribute("message_2", message_2);
        model.addAttribute("spol", spol);

        // Dohvaća weather podatke
        WeatherData weatherData = weatherService.getWeatherData();

        // Provjeri ako je weatherData null
        if (weatherData != null && weatherData.getCurrent() != null) {
            model.addAttribute("weather", weatherData.getCurrent());
        } else {
            // Hendlanje na index.html
            model.addAttribute("weather", null);
        }

        return "index";
    }


}
