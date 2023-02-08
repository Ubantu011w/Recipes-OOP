package main;

import java.util.LinkedList;

public class IngList extends abstractList {
  LinkedList<Ingredient> list;

  public IngList() {
    list = new LinkedList<>();
  }

  protected void add(String name, String uoMeasure, double price) {
    Ingredient temp = new Ingredient(name, uoMeasure, price);
    list.addFirst(temp);
  }

  protected void add(Ingredient ing) {
    list.addFirst(ing);
  }


  protected void change(String ing, String name, String uoMeasure, double price) {
    int index = list.indexOf(search(ing));
    list.get(index).change(name, uoMeasure, price);
  }

  protected String getInfo(String name) {
    int index = list.indexOf(search(name));
    return list.get(index).getInfo();
  }

  public String getIngValues() {
    StringBuilder sb = new StringBuilder();
    for (Ingredient i : list) {
      sb.append(i.getValues() + ",");
    }
    return sb.toString();
  }

  public Ingredient search(String name) {
    for (Ingredient i : list) {
      if (name.equals(i.getName())) {
        return i;
      } 
    }
    return null;
  }

  public boolean remove(String str) {
    Boolean s = list.remove(search(str));
    return s;
  }

  public String print() {
    StringBuilder sb = new StringBuilder();
    for (Ingredient i : list) {
      sb.append(i.getInfo());
      sb.append("\n");
    }
    System.out.println(sb.toString());
    return sb.toString();
  }

}
