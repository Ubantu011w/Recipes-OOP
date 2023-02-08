package main;

public class context {
   Search strategy;

   void setStrategy(Search strategy) {
      this.strategy = strategy;
   }

   String executeStrategy(Recipelist list, IngList ingList) {
      return strategy.Spec_search(list, ingList);
   }
}
