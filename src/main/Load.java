package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Load { 
  public IngList ingrediens() {
    IngList Ingredientslist = new IngList();
    String name = "";
    String measure = "";
    double price = 0;
    try {
      File myObj = new File("Ingredienser.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String element = "";
        int position = 0; 
        for (int i = 0; i < data.length(); i++) {
          
          if (data.charAt(i)!=':') {
            element += data.charAt(i);
          }
          else {
            position += 1;
            if (position == 1) {
              name = element;
            }
            else if (position == 2) {
              measure = element;
            }
            else if (position == 3) {
              price = Double.parseDouble(element);
            }
            element="";
          }
          
        }
        Ingredientslist.add(name,measure,price);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return Ingredientslist;

  } 

  public Recipelist recipes(IngList ingList) {
    Recipelist recipes = new Recipelist();
    String name = "";
    Double portion = 0.0;
    Double price = 0.0;
    String Instructions = "";

    try {
      File myObj = new File("Recipe.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        IngList ingredients_names_portions = new IngList();
        String data = myReader.nextLine();
        String element = "";
        int position = 0;
        for (int i = 0; i < data.length(); i++) {
          if (data.charAt(i)!='?') {
            element += data.charAt(i);
          }
          else {
            position += 1;
            if (position == 1) {
              name = element;
            }
            else if (position == 2) {
              portion = Double.parseDouble(element);
            }
            else if (position == 3) {   // Ingredients in Recipes
              String tempelement = "";
              int tempos = 0;
              Double ingportion = 0.0;
              Ingredient temping = null;
               for (int u = 0; u < element.length(); u++)
               {
                 if (element.charAt(u)!=':' && element.charAt(u)!=',') {
                  tempelement += element.charAt(u);
                 }

                 else if (element.charAt(u)==',') { // New ingredient
                  tempos = 0;
                 }

                 else {
                   tempos +=1;
                   if (tempos == 1) {
                    // try..
                    temping = ingList.search(tempelement);
                   }
                   if (tempos == 3) {
                      ingportion = Double.valueOf(tempelement);
                      if (temping != null){
                        ingredients_names_portions.add(temping.getName(), temping.getUoMeasure(), ingportion);
                      }
                   }
                   tempelement = "";
                 }
                 
               }
               
            }
            else if (position == 4) {
              Instructions = element;
            }
            else if (position == 5) {
              price = Double.parseDouble(element);
            }

            element="";
          }
        }
        recipes.add(name, portion, ingredients_names_portions, Instructions, price);
        }
        
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return recipes;
  }
}


