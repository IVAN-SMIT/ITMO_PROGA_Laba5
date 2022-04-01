package commands;

import City.City;
import auxiliary.Command;
import auxiliary.Messager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;

/**
 * выводит элементы, значение поля carCode которых больше заданного
 */

public class Filter_greater_than_car_codeCommand implements Command {
    public Stack<City> run(String argument, Stack<City> cityCollection) {

            BufferedReader filter = new BufferedReader(new InputStreamReader(System.in));
            Long carCode = null;
             Messager p = new Messager();

            try {
                p.println("Введите значение car code: \n(long)", true);
                carCode = Long.parseLong(filter.readLine());

            } catch (Exception e) {
                e.printStackTrace();
                p.println("Введены неверные данные! Попробуйте снова. (начните с filter_greater_than_car_code)", true);
            }

            Iterator<City> iterator = cityCollection.iterator();
            int count = 0;
            int fin = cityCollection.size();

            while (iterator.hasNext()) {
                count = count + 1;
                City element = iterator.next();
                if (element.getCarCode() > carCode ) {
                    count = count + 1;
                    p.println(element.toString(), true);
                }
            }

        if (count == fin) {p.println("Элементы со значением больше " + carCode + " отсутсвуют!", true);}

            return cityCollection;
        }
    }

