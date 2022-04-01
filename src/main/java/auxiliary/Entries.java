package auxiliary;

import City.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Stack;

/**
 * Метод для ввода данных при добавлении элемента в коллекцию
 */

public class Entries {

    public Stack<City> getData(long id,int index, Stack<City> cityCollection, boolean flag) throws IOException {

        Messager p = new Messager();
        BufferedReader n = new BufferedReader(new InputStreamReader(System.in));

        p.println("Name: ", flag);
        String name = n.readLine();

        p.println("(int)Coordinates [x y]: ", flag);
        String[] xy = n.readLine().trim().split(" ");
        Coordinates coordinates = new Coordinates(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));

        LocalDateTime localDate = LocalDateTime.now();

        p.println("(int)Area: ", flag);
        int area = Integer.parseInt(n.readLine().trim());

        p.println("(long)Population: ", flag);
        Long population = Long.valueOf(n.readLine().trim());

        p.println("(long)Meters above sea level: ", flag);
        Long metersAboveSeaLevel = Long.valueOf(n.readLine().trim());

        p.println("(long)Car code: ", flag);
        long carCode = Long.parseLong(n.readLine().trim());

        p.println("Climate [MONSOON, OCEANIC, MEDITERRANIAN, STEPPE]: ", flag);
        Climate climate = Climate.getEnumByName(n.readLine().trim());

        p.println("Standard of living[VERY_HIGH, HIGH, NIGHTMARE]: ", flag);
        StandardOfLiving standardOfLiving = StandardOfLiving.getEnumByName(n.readLine().trim());

        p.println("(float)Height of Governor:", flag);
        float height = Float.parseFloat(n.readLine());
        Human governor = new Human(height);
        governor.setHeight(height);

        cityCollection.insertElementAt(new City(id, name.trim(), coordinates,
                localDate.toString(),
                area, population, metersAboveSeaLevel,
                carCode, climate, standardOfLiving, governor), index);

        return cityCollection;
    }
}
