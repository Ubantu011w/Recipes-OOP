package main;

public class ConcreteStrategyPrice implements Search {
   @Override
   public String Spec_search(Recipelist recipelist, IngList inglist) {
      
      Consoleui ui = new Consoleui();
      ui.output("Recipes price <= ");
      Double price = ui.getInputDouble();
      String string = "";
      for(Recipe i : recipelist.list)
      {
         if (i.getPrice()<= price)
         {
            string += i.getInfo(null, inglist) + "\n\n";
         }
    	  
      }
      if (string == "") { string = "Couldn't find recipies with lower price";}
      return string;

      
   }
}
