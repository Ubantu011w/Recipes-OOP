package main;

public class Ingredient {
    protected String name;
    protected String uoMeasure;
    protected double price;


    public Ingredient(String name, String uoMeasure, double price) {
        this.name = name;
        this.uoMeasure = uoMeasure;
        this.price = price;
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + name + "\n");
        sb.append("Unit of measure: " + uoMeasure + "\n");
        sb.append("Price: " + price + "$\n");
        return sb.toString();
    }

    public String getValues() { // För att spara recipies i filen Ingredienser.txt
        StringBuilder sb = new StringBuilder();
        sb.append(name + ":");
        sb.append(uoMeasure + ":");
        sb.append(price + ":");
        return sb.toString(); 
    }

    public void change(String name, String uoMeasure, double price) {
        this.name = name;
        this.uoMeasure = uoMeasure;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public String getUoMeasure(){
        return this.uoMeasure;
    }

    public double getPrice(){
        return this.price;
    }

}