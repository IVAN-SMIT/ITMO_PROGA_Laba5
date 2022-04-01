package commands;

import City.City;
import auxiliary.Command;
import auxiliary.Corrector;
import auxiliary.Entries;
import auxiliary.Messager;

import java.util.Stack;
import java.util.UUID;


public class AddCommand implements Command {
    public  Stack<City>  run(String argument, Stack<City> cityCollection) throws IllegalArgumentException {
        if (argument != null) {
            throw new IllegalArgumentException("AddCommand не имеет аргументов");
        }
        Messager p  = new Messager();
        try {

            Long id;
            UUID myId = UUID.randomUUID();
            id = (long) Math.floor(Math.abs(myId.hashCode()/100000));

            Entries city = new Entries();
            city.getData(id, cityCollection.size(), cityCollection, true);

            p.println("Ваша коллекция:\n" + (cityCollection.peek().toString()) +
                    "\nВерно?\n \"1\"-да, все верно\n \"2\"-нет, ввести заново", true);

            boolean flag = true;
            while (flag) {
                int up = Corrector.getInt();
                switch (up) {
                    case 1:
                        p.println("Введите save чтобы сохранить коллекцию или help для отображения списка команд", true);
                        flag = false;
                        break;
                    case 2: {
                        p.println("Начинаем заново.", true);
                        return new AddCommand().run(argument, cityCollection);
                    }
                    default:
                        p.println("Ведите либо 1, либо 2!", true);
                        break;
                }
            }

        }catch (Exception e) {
            new Messager().println("Введены неверные данные! Попробуйте снова. (начните с add)", true);
            // e.printStackTrace();
        }
        return cityCollection;
    }


}



