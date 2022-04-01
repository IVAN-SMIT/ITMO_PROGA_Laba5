import City.City;
import auxiliary.Commander;
import auxiliary.IdChecker;
import manager.Manager;

import java.io.IOException;
import java.util.Stack;

// вариант 751314
//ЛАБА ПИЗДЕЦ

public class Main {

    public static void main(String[] args) throws IOException {

        String filename = args[0];

        Stack<City> cityCollection = new Manager().loadCollection(filename);
        Commander worker = new Commander();
        worker.setCollection(cityCollection);
        System.out.println("==================IVAN SMIT=======================");
        System.out.println("===========Вас приветствует пятая лаба!===========");
        System.out.println("\nВведите help чтобы посмотреть доступные команды");

        while (true) {

            new IdChecker().check(cityCollection);

                try {
                   worker.readCommand(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}
