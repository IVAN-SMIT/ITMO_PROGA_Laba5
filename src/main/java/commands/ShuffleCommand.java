package commands;

import City.City;
import auxiliary.Command;
import auxiliary.Messager;

import java.util.Collections;
import java.util.Stack;

/**
 * перемешивает элементы коллекции в случайном порядке
 */

public class ShuffleCommand implements Command {
    public  Stack<City> run(Stack<City> cityCollection) {
        Collections.shuffle(cityCollection);
        new Messager().println("Элементы перемешаны!", true);
        return cityCollection;
    }
}
