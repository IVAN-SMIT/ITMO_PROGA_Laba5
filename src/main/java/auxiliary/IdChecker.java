package auxiliary;

import City.City;
import commands.Remove_by_idCommand;
import commands.SaveCommand;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;

public class IdChecker {

     /**Проверяет все элементы на уникальность id, предлагает удалить повторы
     * @param cityCollection - имя файла где находятся элементы коллекции
     */

    public static void check(Stack<City> cityCollection ) throws IOException {

        Iterator<City> iterator = cityCollection.iterator();
        Messager check = new Messager();
        boolean flag = true;
        Long array[] = new Long[cityCollection.size()];

            while (iterator.hasNext()) {
                for (int i = 0; i < cityCollection.size(); i++) {
                    long id = iterator.next().getId();
                    array[i] = id;
                }
            }

        for(int i=0; i<cityCollection.size(); i++) {
            for (int j=i+1; j<cityCollection.size(); j++) {

             if(Objects.equals(array[i], array[j])) {

                check.println("Найдено повторение id перед позицией " + j, flag);
                check.println(Arrays.toString(array), flag);
                check.println("удалить элемент?",flag);
                check.println( " \"1\"-да, элемент будет удален\n \"2\"-нет, завершить работу программы и исправить неполадки вручную", flag);

                boolean error = true;

                while (error) {
                    int up = Corrector.getInt();
                    switch (up) {
                        case 1:
                            new Remove_by_idCommand().run(String.valueOf(array[i]), cityCollection);
                            new SaveCommand().run("", cityCollection);
                            error = false;
                            break;
                        case 2: {
                            check.println("Надеюсь, вы устраните неполадки", true);
                            System.exit(0);
                        }
                        default:
                            check.println("Ведите либо 1, либо 2!", true);
                            break;
                    }
                }

            }
        }
    }

    }
}

