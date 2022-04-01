package commands;

import City.City;
import auxiliary.Command;
import manager.Manager;
import java.util.Stack;

/**
 * Сохраняет коллекцию в файл
 */

public class SaveCommand implements Command {
    public void run(String argument, Stack<City> cityCollection){
        if (argument == null) {
            new Manager().saveCollection(cityCollection, null);
        } else {
           new Manager().saveCollection(cityCollection, argument);
        }
    }
}
