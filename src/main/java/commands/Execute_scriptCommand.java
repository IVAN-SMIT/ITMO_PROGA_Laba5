package commands;

import City.*;
import auxiliary.Command;
import auxiliary.Commander;
import manager.Manager;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Запускает команды из файла
 */

public class Execute_scriptCommand implements Command {
    public void run(String file, Stack<City> cityCollection) throws Exception {
        ArrayList<String> commandsArr = new Manager().script(file);
        for (String command : commandsArr) {
            if (command.split(" ").length == 2 && command.split(" ")[0].equals("execute_script") && command.split(" ")[1].equals(file)) {
                throw new IllegalArgumentException("Ошибка зацикливания!");
            }
            new Commander().readCommand(command);
        }
    }
}