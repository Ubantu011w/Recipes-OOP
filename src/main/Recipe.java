package main;
import java.lang.Math;

public class Recipe {
    protected String name;
    protected double portion;
    protected IngList ingredients_names_portions;
    protected String Instructions;
    protected double price;

    public Recipe(String name, double portion, IngList ingredients_names_portions, String Instructions, double price) {
        this.name = name;
        this.portion = portion;
        this.ingredients_names_portions = ingredients_names_portions;
        this.Instructions = Instructions;
        this.price = price;
    }

    public String getInfo(Double amount, IngList maininglist) {
        StringBuilder sb = new StringBuilder();
        double difference = 0;
        double ingprice = 0.0;
        double outprice = 0.0;
        
        sb.append("Name: " + name + "\n");
        if (amount != null) {
            sb.append("Portions: " + amount + "\n");
            if (amount < portion || amount > portion) {
                difference = amount / portion;
            }
            else {
                difference = 1;
            }

        }
        else {
            sb.append("Portions: " + portion + "\n");
            outprice = price;
        }
            sb.append("Ingredients:\n");
        for (int i = 0; i <ingredients_names_portions.list.size(); i++) {
            Ingredient temping = ingredients_names_portions.list.get(i); // To get ingredient portion
            double amounting = temping.getPrice(); // Get ingredient portion from the inglist of the recipe
            if (amount != null) {
                Ingredient tempingmain = maininglist.search(temping.getName()); // to get ingredient price from the main ing list
                amounting *= difference;                                          
                if (temping.getUoMeasure().equals("piece")) {
                        amounting = Math.round(amounting);
                        }
                ingprice = amounting * tempingmain.getPrice();
                outprice += ingprice;
                    }
            sb.append(amounting + ", ");
            sb.append(temping.getUoMeasure() + ", ");
            sb.append(temping.getName() + "\n");
            }
            
    
        sb.append("Instructions: " + Instructions + "\n");
        sb.append("Price: " + outprice + "$");

        return sb.toString();
    }

    public String getValues() { // För att spara recipies i filen Recipe.txt
        StringBuilder sb = new StringBuilder();
        sb.append(name + "?");
        sb.append(portion + "?");
        sb.append(ingredients_names_portions.getIngValues() + "?");
        sb.append(Instructions + "?");
        sb.append(price + "?");
        return sb.toString(); 
    }

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return price;
    }

    public double getportion() {
        return this.portion;
    }

    public IngList getingrediens_names() {
      return this.ingredients_names_portions;
    }

    public String getInstructions() {
      return Instructions;
    }

    public void Setingrediens_names(IngList inglist) {
        this.ingredients_names_portions = inglist;
    }

}