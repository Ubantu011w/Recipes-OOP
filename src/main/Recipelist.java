package main;

import java.util.LinkedList;

public class Recipelist extends abstractList {
  LinkedList<Recipe> list;

  public Recipelist() {
    list = new LinkedList<>();
  }

  protected void add(String name, double portion, IngList ingrediens_names_portions, String Instructions, double price) {
    Recipe temp = new Recipe(name, portion, ingrediens_names_portions, Instructions, price);
    list.addFirst(temp);
  }

  protected void add(Recipe ing) {
    list.addFirst(ing);
  }

  protected boolean remove(String str) {
    boolean s = list.remove(search(str));
    return s;
  }

  protected String getInfo(String name, Double amount, IngList ing) { 
    int index = list.indexOf(search(name));
    return list.get(index).getInfo(amount, ing);
  }

  public Recipe search(String name) {
    for (Recipe i : list) {
      if (name.equals(i.getName())) {
        return i;
      }
    }
    return null;
  }

  public String print() {
    StringBuilder sb = new StringBuilder();
    for (Recipe i : list) {
      sb.append(i.getInfo(null, null));
      sb.append("\n");
      sb.append("\n");
    }
    System.out.println(sb.toString());
    return sb.toString();
  }

  public void IngRemoved(String ingredient, Double IngPrice) { // if we delete an ingredient it will be deleted from the recipies too
    for (Recipe i : list) {
      IngList temp = i.ingredients_names_portions;
      Ingredient tempIngredient = temp.search(ingredient);
      if (tempIngredient != null) {
        double ingAmount = tempIngredient.getPrice();
        i.Instructions += " (NOTE! ingredient " + tempIngredient.getName() + " has been removed from ingredients list)";
        temp.remove(ingredient);
        i.price -= (IngPrice * ingAmount);
        round(i.price, 3);
        if (temp.list.size()==0) {
          i.price = 0;
        }
      }
    }

  }

  public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}

}
