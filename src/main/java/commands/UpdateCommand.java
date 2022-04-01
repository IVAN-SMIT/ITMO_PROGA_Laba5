package commands;

import City.City;
import auxiliary.Command;
import auxiliary.Corrector;
import auxiliary.Entries;
import auxiliary.Messager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;

/**
 * Обновляет элемент коллекции по его id, согласно синтаксису
 */

public class UpdateCommand implements Command {
    public Stack<City> run(String argument, Stack<City> cityCollection) throws IOException {
        Iterator<City> iterator = cityCollection.iterator();
        BufferedReader u = new BufferedReader(new InputStreamReader(System.in));
        Messager update = new Messager();

        while (iterator.hasNext()) {
            City element = iterator.next();
            if (argument == null) {
                new Messager().println("Введите id элемента: ", true);
                argument = u.readLine();
            }

            if (element.getId() == Integer.parseInt(argument)) {
                iterator.remove();
                break;
            }
        }

        try {
            Long id = Long.parseLong(argument);
            Entries city = new Entries();
            city.getData(id, cityCollection.size(), cityCollection, true);

            update.println("Ваша коллекция:\n" + (cityCollection.peek().toString()) +
                    "\nВерно?\n \"1\"-да, все верно\n \"2\"-нет, ввести заново", true);


            boolean flag = true;
            while (flag) {
                int up = Corrector.getInt();
                switch (up) {
                    case 1:
                        update.println("Введите save чтобы сохранить коллекцию или help для отображения списка команд", true);
                        flag = false;
                        break;
                    case 2: {
                        update.println("Начинаем заново.", true);
                        return new UpdateCommand().run(argument, cityCollection);
                    }
                    default:
                        update.println("Ведите либо 1, либо 2!", true);
                        break;
                }
            }
        } catch (Exception e) {
            update.println("Введены неверные данные! Попробуйте снова. (начните с update + id элемента)", true);

        }
        return cityCollection;
    }
}

