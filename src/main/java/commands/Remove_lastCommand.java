package commands;

import City.City;
import auxiliary.Command;
import auxiliary.Messager;

import java.util.Stack;

/**
 * удаляет последний элемент из коллекции
 */

public class Remove_lastCommand implements Command {
    public  Stack<City> run( Stack<City> cityCollection) {

            cityCollection.peek();
            new Messager().println("Удален элемент:\n" + cityCollection.pop().toString()
                    +"\nНе забывайте сохранять изменения с помощью команды 'save'", true);
             return cityCollection;
        }

    }


