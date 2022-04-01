package auxiliary;

import City.City;
import commands.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;


/**
 * Отвечает за выполнение всех команд приложения, запоминает введенные команды в LinkedList
 */


public class Commander {
    private static Stack<City> cityCollection;

    private static LinkedList<String> history = new LinkedList<>();

    public void setCollection(Stack<City> IcityCollection) {
        cityCollection = IcityCollection;
    }

    public void readCommand(String cmnd) throws Exception {
        Messager report = new Messager();
        boolean flag = true;
        String command;
        BufferedReader n = new BufferedReader(new InputStreamReader(System.in));

           if (cmnd == null) {
               command = n.readLine();

           } else {
               command = cmnd;
           }
           String argument = null;


           if (command.split(" ").length > 2) {
               report.println("Слишком много значений введено!", flag);
           } else if (command.split(" ").length == 2) {
               argument = command.split(" ")[1];
           }

        command = command.split(" ")[0];


        if (history.size() == 11) {
            history.remove();
        }

        history.add(command);

        switch (command) {
            case"help": new HelpCommand().run();break;
            case "exit":  new ExitCommand().run(argument);break;
            case "info": new InfoCommand().run(argument, cityCollection);break;
            case "3137best": pashalOchka.run(argument);break;// интересно, а что же это......
            case "history": new HistoryCommand().run(argument, history);break;
            case "show": new ShowCommand().run(argument, cityCollection);break;
            case "clear": new  ClearCommand().run(argument, cityCollection);break;
            case "add":  new AddCommand().run(argument, cityCollection);break;
            case "save":  new SaveCommand().run(argument, cityCollection);break;
            case "remove_by_id": new Remove_by_idCommand().run(argument, cityCollection);break;
            case "remove_last": new Remove_lastCommand().run(cityCollection);break;
            case "update": new UpdateCommand().run(argument, cityCollection);break;
            case "execute_script" : new Execute_scriptCommand().run(argument, cityCollection);break;
            case "remove_any_by_climate" : new Remove_any_by_climateCommand().run(argument, cityCollection);break;
            case "filter_greater_than_car_code" : new Filter_greater_than_car_codeCommand().run(argument, cityCollection);break;
            case "insert_at" : new Insert_atCommand().run(argument, cityCollection);break;
            case "shuffle" : new ShuffleCommand().run(cityCollection);break;
            default: report.println("Неопознанная команда. Введите 'help' для просмотра доступных команд", flag);
        }
    }
}

