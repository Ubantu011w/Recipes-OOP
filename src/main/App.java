package main;

import java.io.File;

public class App {
  Consoleui ui = new Consoleui();
  Load load = new Load();
  Save save = new Save();
  IngList inglist = new IngList();
  Recipelist recipelist = new Recipelist();
  context search = new context(); 
  
  public void start() {
     // Checking the files if not empty we load.
    File ingredients = new File("Ingredienser.txt");
    if (ingredients.length() != 0) {
      inglist = load.ingrediens();
    }
    File Recipelist = new File("Recipe.txt");
    if (Recipelist.length() != 0) {
      recipelist = load.recipes(inglist);
    }

    Boolean ongoing = true;
    //Everything starts here.
    while(ongoing) {
      ui.output("Choose:\n1. Manage ingredients.\n2. Manage recipes.\n3. Save and quit.");
      int i = ui.getInputInt();
      if (i==1) {
        sub_ingredients();
      }

      if (i==2) {
        sub_recipe();
      }

      if (i==3) {
        //Saving data to files.
        save.Ingrediens_save(inglist);
        save.Recipe_save(recipelist);
        ongoing = false;
      }
    }
  }

  private void sub_ingredients() {
      ui.output("Ingredients.");
      ui.output("1. Create an ingredient\n2. List all ingredients\n3. Get info\n4. Delete an ingredient");
      Consoleui.input a = this.ui.promptForInput();
      switch (a) {
      case CREATE:
        ui.output("Enter an ingredient name:");
        String name = ui.getInputString();
        ui.output("Enter a measurement:");
        String uoMeasure = ui.getInputString();
        ui.output("Enter a price:");
        double price = ui.getInputDouble();
        inglist.add(name, uoMeasure, price);
        break;
      case LISTALL:
      inglist.print();
        break;
      case GETINFO:
        ui.output("Enter the name of ingredient:");
        String string = ui.getInputString();
        try {
          String info = inglist.getInfo(string);
          ui.output(info);
        } catch (Exception e) {
          ui.output("Couldn't find the ingredient.");
        }
        break;
      case DELETE:
        ui.output("Enter the name of ingredient:");
        String delete = ui.getInputString();
        Ingredient tempIng = inglist.search(delete);
        if (tempIng != null) {
          double tempprice = tempIng.getPrice();
          recipelist.IngRemoved(delete,tempprice);
          inglist.remove(delete);
          ui.output(delete + " Deleted succesfully.");
        }
        else {
          ui.output("Couldn't find the ingredient.");
        }

        
        break;
      case BASEDSEARCH:
        break;   
      default:
        break;
    
      }
  }   

  private void sub_recipe() {
    ui.output("Recipes.");
    ui.output("1. Create a recipe\n2. List all recipies\n3. Get info\n4. Delete a recipe\n5. Search by price or ingredients\n");
    Consoleui.input a = this.ui.promptForInput();
    switch (a) {
      case CREATE:
        double price = 0.0;
        ui.output("Enter a recipe name: ");
        String name = ui.getInputString();
        ui.output("How many portions: ");
        Double portion = ui.getInputDouble();
        ui.output("Enter how many ingredients would you need (int):");
        int number = ui.getInputInt();
        IngList ing_list_temp = new IngList();

        for (int i=1; i<=number; i++) {
          ui.output("Enter ingredient's name (" + i + "): " );
          String ing_name = ui.getInputString();
          Ingredient temping = inglist.search(ing_name);
          if (temping != null) {     // if it returns null then there is no such ingredient
            ui.output("Enter how many " + temping.getUoMeasure() + " you need of " + temping.getName() + " (double): ");
            double ing_portion = ui.getInputDouble();
            double price_temp = temping.getPrice();
            price_temp *= ing_portion;
            price += price_temp;
            ing_list_temp.add(temping.getName(), temping.getUoMeasure(), ing_portion);;
          }
          else {
            ui.output("Ingredient not found in the list.");
          }
        }

        ui.output("Enter instructions: ");
        String instructions = ui.getInputString();
        recipelist.add(name, portion, ing_list_temp, instructions, price);
        
        break;
      case LISTALL:
      recipelist.print();
        break;
      case GETINFO:
      ui.output("Enter the name of the recipe: ");
      String recipe_name = ui.getInputString();
      if (recipelist.search(recipe_name) == null) {
        ui.output("Recipe not found.");
        break;
      }
      else {
        ui.output("How many portions (double): ");
        double recipe_portion = ui.getInputDouble();
        ui.output(recipelist.getInfo(recipe_name, recipe_portion, inglist)); //
      }
        break;
      case DELETE:
      ui.output("Enter the name of the recipe:");
      recipe_name = ui.getInputString();
        if(recipelist.remove(recipe_name)) {
          ui.output(recipe_name + " Deleted succesfully.");
        }
      else {
        ui.output("Couldn't find the recipe.");
      }
  
        break;
      case BASEDSEARCH: // Search by price or ingredient name
        ui.output("1. Search by price.\n2. Search by ingredient name.\n");
        int numinput= ui.getInputInt();
        if(numinput == 1){ search.setStrategy(new ConcreteStrategyPrice());}
        if(numinput == 2) { search.setStrategy(new ConcreteStrategyIngName());}
        String output = search.executeStrategy(recipelist, inglist);
        ui.output(output + "\n");
        break;   
      default:
        break;
    }
  
    
  }

}

