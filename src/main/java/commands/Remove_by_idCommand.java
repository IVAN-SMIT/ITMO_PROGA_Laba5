package commands;

import City.City;
import auxiliary.Command;
import auxiliary.Messager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;

/**
 * удаляет элемент из коллекции по его id
 */

public class Remove_by_idCommand implements Command {
    public  Stack<City> run(String argument, Stack<City> cityCollection) throws IOException {
        Iterator<City> iterator = cityCollection.iterator();
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        Messager remove = new Messager();
        boolean flag = true;

        if(argument == null) {
            remove.println("Введите id элемента: ", flag);
            argument =r.readLine();
        }

        try {
            remove.println("Элемент:", flag);
            Long numId = Long.valueOf(argument);
            while (iterator.hasNext()) {
                City element = iterator.next();
                if (element.getId().equals(numId)) {
                    remove.println(element.toString(), flag);
                    //System.out.println(iterator.next());
                    iterator.remove();
                    remove.println("Со значением id:" + numId + " был удалён.",flag);
                    //remove.println("Не забывайте про 'save' чтобы сохранить изменения.",flag);
                    break;
                }
            }

        }catch (Exception i){
            //i.printStackTrace();
            remove.println("Введены неверные данные! Попробуйте снова. \n(начните с remove_by_id + id желаемого элемента)", flag);

        }
            return cityCollection;
        }
    }



