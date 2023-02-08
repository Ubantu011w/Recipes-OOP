package main;
import java.util.Scanner;

public class Consoleui {
  Scanner myobj;
  public enum input {
    CREATE,
    LISTALL,
    GETINFO,
    DELETE,
    BASEDSEARCH,
    None,
  }

public input promptForInput() {

  int c;
  
  do {
    c = getInputInt();
  } while (!(c == 1 || c == 2 || c == 3 || c == 4 || c == 5 || c == 6));

  switch (c) {
    case 1: return input.CREATE;
    case 2: return input.LISTALL;
    case 3: return input.GETINFO;
    case 4: return input.DELETE;
    case 5: return input.BASEDSEARCH;
    default: break;
  }

  return input.None;
}

public void output(String string)
{
  System.out.println(string);
}

public int getInputInt() {
    myobj = new Scanner(System.in);
    int c = myobj.nextInt();
    return c;

}

public double getInputDouble() {
  myobj = new Scanner(System.in);
  double c = myobj.nextDouble();
  return c;

}

public String getInputString() {
    myobj = new Scanner(System.in);
    String word = myobj.nextLine();
    return word;
}


}
