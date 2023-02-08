package main;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

class Save {
  public void Ingrediens_save(IngList ingredients) {
    LinkedList<Ingredient> listofing =  ingredients.list;
    try {   
      FileWriter myWriter = new FileWriter("Ingredienser.txt");
      for (int i = 0; i < listofing.size(); i++) {
        myWriter.write(listofing.get(i).getValues());
        myWriter.write("\n");
      }
      
      myWriter.close();
      System.out.println("Successfully Saved Ingredients.");
      
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
}

  public void Recipe_save(Recipelist recipe) {
    LinkedList<Recipe> listofrecp = recipe.list;
    try {
      FileWriter myWriter = new FileWriter("Recipe.txt");
      for (int i = 0; i < listofrecp.size(); i++) {
        myWriter.write(listofrecp.get(i).getValues());
        myWriter.write("\n");
      }
      myWriter.close();
      System.out.println("Successfully Saved Recipes.");

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
