package com.example.apitest;

public class BeerDescriptionModel {
    private String brand;
    private String name;
    private String style;
    private String alcohol;

    public BeerDescriptionModel(String brand, String name, String style, String alcohol) {
        this.brand = brand;
        this.name = name;
        this.style = style;
        this.alcohol = alcohol;
    }

    public BeerDescriptionModel(){

    }

    public String getBeerdescription(){
        return "Brand " + brand + "\n" + "Name " + name
                + "\n" + "Style " + style + "\n" + "Alcohol " + alcohol;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }
}
