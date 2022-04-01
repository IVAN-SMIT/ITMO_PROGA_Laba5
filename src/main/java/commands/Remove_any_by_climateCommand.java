package commands;

import City.*;
import auxiliary.Command;
import auxiliary.Messager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;

/**
 * Удаляет из коллекции один элемент, значение поля climate которого эквивалентно заданному (задается вручную)
 */

public class Remove_any_by_climateCommand implements Command {
    public  Stack<City> run(String argument,Stack<City> cityCollection ){

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        Climate climate = null;
        Messager remove = new Messager();
        boolean flag = true;

        try{
            remove.println("Выберите значение Climate \n[MONSOON, OCEANIC, MEDITERRANIAN, STEPPE]: ", flag);
            Climate climate1 = Climate.getEnumByName(r.readLine());
            climate = climate1;
        }catch (Exception e){
            e.printStackTrace();
            remove.println("Введены неверные данные! Попробуйте снова. (начните с remove_any_by_climate)", flag);
        }
        Iterator<City> iterator = cityCollection.iterator();
            int count = 0;
            int fin = cityCollection.size();
        while (iterator.hasNext()) {
            count = count + 1;
            City element = iterator.next();
            // System.out.println(element.getClimate());  //отладка

            if (element.getClimate().toString().equals(climate.toString())) {
                iterator.remove();
                count = count + 1;
                remove.println("Удален элемент:\n " + element + "\n  со значением поля Climate: " + climate+"\n", flag);
                remove.println("Введите 'save' чтобы сохранить изменения", flag);

            } else  if (count == fin) {
                remove.println("Элементы со значением " + climate + " отсутсвуют!", flag);
            }
        }


        return cityCollection;
    }
}
