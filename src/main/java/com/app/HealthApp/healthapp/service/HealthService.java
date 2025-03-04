package com.app.HealthApp.healthapp.service;

import com.app.HealthApp.healthapp.model.UserInput;
import org.springframework.stereotype.Service;

@Service
public class HealthService {


    public double calculateBMI(UserInput userInput){
        int height = userInput.getHeight();
        int weight = userInput.getWeight();

        double heightInMeters = height / 100.0;
        double bmi = weight / (heightInMeters * heightInMeters);
        System.out.println(bmi);
        return bmi;
    }

    public double calculateBodyFat(UserInput userInput){
        int age = userInput.getAge();
        String sex = userInput.getSex();

        double bodyFatPercentage;
        if ("MUSKO".equals(sex)){
            bodyFatPercentage = (1.20 * calculateBMI(userInput))
                    + (0.23 * age) - 16.2;
        } else {
            bodyFatPercentage = (1.20 * calculateBMI(userInput))
                    + (0.23 * age) - 5.4;
        }
        System.out.println(bodyFatPercentage);
        return bodyFatPercentage;
    }

    public double calculateFFMI(UserInput userInput){
        int height = userInput.getHeight();
        int weight = userInput.getWeight();
        String sex = userInput.getSex();
        double BFP = calculateBodyFat(userInput);

        double fatFreeMass = weight * (1 - (BFP / 100));

        double heightInMeters = height / 100.0;

        double ffmi = fatFreeMass / (heightInMeters * heightInMeters);

        if (sex.equalsIgnoreCase("MUSKO")) { // For men
            ffmi += 6.1 * (1.8 - heightInMeters);
        }
        System.out.println("BFP: " + BFP);
        System.out.println("SEX: " + sex);
        System.out.println("FFMI: " + ffmi);
        return ffmi;
    }

    public double calculateBMR(UserInput userInput) {
        int weight = userInput.getWeight();
        int height = userInput.getHeight();
        int age = userInput.getAge();
        String sex = userInput.getSex();

        double bmr;
        if ("MUSKO".equalsIgnoreCase(sex)) {

            bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
        } else {

            bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
        }
        System.out.println(bmr);
        return bmr;
    }

    public double calculateProtein(UserInput userInput){
        int weight = userInput.getWeight();

        double protein = weight * 0.8;
        System.out.println(protein);
        return protein;
    }

}
