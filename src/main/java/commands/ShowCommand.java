package commands;

import City.*;
import auxiliary.Command;
import auxiliary.Messager;

import java.util.Stack;
import java.util.Iterator;

/**
 * Показывает все существующие элементы коллекции
 */

public class ShowCommand implements Command {
    public void run(String argument, Stack<City> cityCollection) throws IllegalArgumentException {
        if (argument != null) {
            throw new IllegalArgumentException("Show не имеет аргументов!");}
        Messager show = new Messager();
    try {
        Iterator<City> iterator = cityCollection.iterator();
        if (cityCollection.size() == 0) {
            show.println("В коллекции отсутствуют элементы!\n", true);
        }
        while (iterator.hasNext()) {show.println(iterator.next().toString(), true);}

         }catch (Exception e){
             new Messager().println("Ошибка!", true);
        }
    }
}