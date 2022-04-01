package commands;

import City.City;
import auxiliary.Command;
import auxiliary.Messager;

import java.util.*;

/**
 * очищает коллекцию
 */

public class ClearCommand implements Command {
 public  Stack<City> run(String argument, Stack<City> cityCollection) throws IllegalArgumentException{
     if (argument != null) {
         throw new IllegalArgumentException("Clear не имеет аргументов!");
     }

     while (!cityCollection.empty()) {
         cityCollection.pop();
     }
    new Messager().println("Все коллекции удалены!", true);
         return cityCollection;
 }
}
