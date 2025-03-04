package com.app.HealthApp.service;

import com.app.HealthApp.healthapp.model.UserInput;
import com.app.HealthApp.healthapp.service.HealthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthServiceTest {

    @InjectMocks
    private HealthService healthService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateBMI() {
        UserInput userInput = new UserInput();
        userInput.setHeight(180); // 180 cm
        userInput.setWeight(75);   // 75 kg

        double bmi = healthService.calculateBMI(userInput);
        assertEquals(23.148148148148145, bmi, 0.1); // Očekivani BMI je 23.1
    }

    @Test
    void testCalculateBodyFatForMale() {
        UserInput userInput = new UserInput();
        userInput.setHeight(180); // 180 cm
        userInput.setWeight(75); // 75 kg
        userInput.setAge(30);    // 30 godina
        userInput.setSex("MUSKO");

        double bodyFat = healthService.calculateBodyFat(userInput);
        assertEquals(18.477777777777774, bodyFat, 0.1); // Očekivani body fat postotak za muškarce
    }

    @Test
    void testCalculateBodyFatForFemale() {
        UserInput userInput = new UserInput();
        userInput.setHeight(160); // 160 cm
        userInput.setWeight(60); // 60 kg
        userInput.setAge(25);    // 25 godina
        userInput.setSex("ZENSKO");

        double bodyFat = healthService.calculateBodyFat(userInput);
        assertEquals(28.475, bodyFat, 0.1); // Očekivani body fat postotak za žene
    }

    @Test
    void testCalculateFFMIForMale() {
        UserInput userInput = new UserInput();
        userInput.setHeight(180); // 180 cm
        userInput.setWeight(75); // 75 kg
        userInput.setAge(30);    // 30 godina
        userInput.setSex("MUSKO");

        double ffmi = healthService.calculateFFMI(userInput);
        assertEquals(18.87088477366255, ffmi, 0.1); // Očekivani FFMI za muškarce
    }

    @Test
    void testCalculateFFMIForFemale() {
        UserInput userInput = new UserInput();
        userInput.setHeight(160); // 160 cm
        userInput.setWeight(60); // 60 kg
        userInput.setAge(25);    // 25 godina
        userInput.setSex("ZENSKO");

        double ffmi = healthService.calculateFFMI(userInput);
        assertEquals(16.763671875, ffmi, 0.1); // Očekivani FFMI za žene
    }

    @Test
    void testCalculateBMRForMale() {
        UserInput userInput = new UserInput();
        userInput.setHeight(180); // 180 cm
        userInput.setWeight(75); // 75 kg
        userInput.setAge(30);    // 30 godina
        userInput.setSex("MUSKO");

        double bmr = healthService.calculateBMR(userInput);
        assertEquals(1730.0, bmr, 0.1); // Očekivani BMR za muškarce
    }

    @Test
    void testCalculateBMRForFemale() {
        UserInput userInput = new UserInput();
        userInput.setHeight(160); // 160 cm
        userInput.setWeight(60); // 60 kg
        userInput.setAge(25);    // 25 godina
        userInput.setSex("ZENSKO");

        double bmr = healthService.calculateBMR(userInput);
        assertEquals(1314.0, bmr, 0.1); // Očekivani BMR za žene
    }

    @Test
    void testCalculateProtein() {
        UserInput userInput = new UserInput();
        userInput.setWeight(75); // 75 kg

        double protein = healthService.calculateProtein(userInput);
        assertEquals(60.0, protein, 0.1); // Očekivani unos proteina
    }
}