package main;

public class ConcreteStrategyIngName implements Search {
   @Override
   public String Spec_search(Recipelist recipelist, IngList inglist) {
      Consoleui ui = new Consoleui();
      ui.output("Give me a name: ");
      String name = ui.getInputString();
      String string = "";
      for(Recipe i : recipelist.list)
      {
         for (Ingredient s : i.ingredients_names_portions.list) {
                  if (s.getName().equals(name)) {
                     string += i.getInfo(null, inglist) + "\n\n";
                  }
            }
         }
    	  
      if (string == "") { string = "Couldn't find recipies with such ingredient";}
      return string;
   }
   
}
