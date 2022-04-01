package commands;

import City.City;
import auxiliary.Command;
import auxiliary.Messager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;
import java.util.TimeZone;

/**
 * Показывает тип коллекции, дату ее создания и количество элементов в ней
 */

public class InfoCommand implements Command {

    public void run(String argument, Stack<City> stackInfo) throws IllegalArgumentException {
        if (argument != null) {
            throw new IllegalArgumentException("Info не имеет аргументов!");
        }

        try {

            Calendar c = null;

            File file = new File("cityCollection");
            Path files = Paths.get("cityCollection");

            SimpleDateFormat format = null;

            BasicFileAttributes attr =
                    Files.readAttributes(files, BasicFileAttributes.class);

            if (file.exists()) {
                Long lastModified = file.lastModified();
                format = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
                c = Calendar.getInstance();
                c.setTimeInMillis(lastModified);
                c.setTimeZone(TimeZone.getDefault());
            }

            new Messager().println("Тип коллекции: " + stackInfo.getClass().getSimpleName() + " \nДата последнего изменения: " +
                    format.format(c.getTime()) +"\nДата создания: " + format.format(new Date(attr.creationTime().toMillis())) + "\nКоличество элементов: " + stackInfo.size(), true);

        } catch (Exception e) {
            new Messager().println("Произошла ошибка!", true);
            }
        }
    
    }
    



