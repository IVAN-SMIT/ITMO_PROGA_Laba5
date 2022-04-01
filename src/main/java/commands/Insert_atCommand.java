package commands;

import City.City;
import auxiliary.Command;
import auxiliary.Corrector;
import auxiliary.Entries;
import auxiliary.Messager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.UUID;

/**
 * добавляет новый элемент в заданную позицию
 * позиция указывается вручную
 */


public class Insert_atCommand implements Command {
    public Stack<City> run(String argument, Stack<City> cityCollection) throws Exception {

        int index;
        Messager insert = new Messager();
        BufferedReader i = new BufferedReader(new InputStreamReader(System.in));

        if(argument == null) {
            int size = cityCollection.size() +1;
            insert.println("Введите индекс позиции элемента\n Максимально возможное значение: " + size, true);
            argument =i.readLine();
        }

        index = Integer.parseInt(argument) - 1;

        if (index > cityCollection.size() | index <= 0){
            throw new Exception("А ничего, что в коллекции всего " + cityCollection.size() +" элементов???" +
                    "\n Начинай заново с 'insert_at'");}

        insert.println("Теперь создайте новый элемент:", true);

                try {

                    UUID myId = UUID.randomUUID();
                    long id = (long) Math.floor(Math.abs(myId.hashCode()/100000));

                    Entries city = new Entries();
                    city.getData(id, index, cityCollection, true);

                    insert.println("Ваша коллекция:\n" + (cityCollection.get(index).toString()) +
                            "\nВерно?\n \"1\"-да, все верно\n \"2\"-нет, ввести заново", true);


                    boolean flag = true;
                    while (flag) {
                        int up = Corrector.getInt();
                        switch (up) {
                            case 1:
                                insert.println("Введите save чтобы сохранить коллекцию или " +
                                        "help для отображения списка команд", true);
                                flag = false;
                                break;
                            case 2: {
                                insert.println("Начинаем заново.", true);
                                return new Insert_atCommand().run(argument, cityCollection);
                            }
                            default:
                                insert.println("Ведите либо 1, либо 2!", true);
                                break;
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    insert.println("Введены неверные данные! Попробуйте снова. " +
                            "(начните с insert_at + номер желаемой позиции элемента)", true);

                }
                return cityCollection;
            }
        }



