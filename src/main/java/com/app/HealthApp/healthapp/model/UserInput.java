package com.app.HealthApp.healthapp.model;


public class UserInput {

    private String sex;
    private int age;
    private int height;
    private int weight;
    private int waist;

    public String getSex(){
        return sex;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    // Getter for height
    public int getHeight() {
        return height;
    }
    // Setter for height
    public void setHeight(int height) {
        this.height = height;
    }
    // Getter for weight
    public int getWeight() {
        return weight;
    }
    // Setter for weight
    public void setWeight(int weight) {
        this.weight = weight;
    }
    // Getter for waist
    public int getWaist() {
        return waist;
    }
    // Setter for waist
    public void setWaist(int waist) {
        this.waist = waist;
    }
}
