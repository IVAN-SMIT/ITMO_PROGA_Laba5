package manager;

import City.City;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;


public class Manager {
    private static String fileName;
    public static String date;
    public static String date2;
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * Управляет записью и чтением коллекции из файла
     * @param fileName имя файла
     */

    public  String read(String fileName) {
        if (fileName == null){fileName = "cityCollection";}
        try {
            BufferedReader stream = new BufferedReader(new FileReader(fileName));
            int bufferSize = 2048;
            char[] buffer = new char[bufferSize];
            StringBuilder out = new StringBuilder();
            Reader in = new BufferedReader(stream);
            for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0; ) {
                out.append(buffer, 0, numRead);
            }
            in.close();
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     *  Загружает коллекции с файла
     * @param fname имя файла
     * @return cityCollection
     */


    public Stack<City> loadCollection(String fname) throws JsonProcessingException, FileNotFoundException {

        fileName = fname;
        date = java.time.LocalDateTime.now().toString();

        String time;
        File file = new File(fileName);
        date2 = String.valueOf(file.lastModified());

        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Такого файла нет! Создан новый.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка!");
        }

        String jsonCity = new Manager().read(fileName);
        Stack<City> cityCollection = new Stack<>();
        mapper.registerModule(new JSR310Module());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        try {

            cityCollection = mapper.readValue(jsonCity, new TypeReference<Stack<City>>() {
            });


        } catch (IOException y) {
            System.out.println("Файл поврежден или в нем отсутствуют элементы!");
            //y.printStackTrace();
        }

        return cityCollection;
    }

    /**
     * метод для сохранений коллекций
     *
     * @param cityCollection коллекция
     * @param pathToSave путь сохранения
     */

    public void saveCollection(Stack<City> cityCollection, String pathToSave){
        String path ;
        String result = "";
        String time = null;

        try {
            result = mapper.writeValueAsString(cityCollection);

            System.out.println("Коллекция сохранена!");
        } catch (JsonProcessingException ex) {
            System.out.println("Не удалось перевести коллекцию в Json.");
            ex.printStackTrace();
        }

        try {
            if (pathToSave != null) {
                path = pathToSave;
            } else {
                path = fileName;
            }

            time =  mapper.writeValueAsString(date2);
            FileOutputStream fOut = new FileOutputStream(fileName);
            PrintWriter myOutWriter = new PrintWriter(fOut);
            myOutWriter.append(result);
            //myOutWriter.write(System.getProperty("line.separator") + time);

            myOutWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *Метод для чтения файлов, в которых могут быть записаны команды
     * @param filename - имя файла
     *
     */

    public ArrayList<String> script(String filename){

        ArrayList<String> commands = new ArrayList<String>();


        try (
                FileReader reader = new FileReader(filename);
                BufferedReader buf = new BufferedReader(reader)
        ){
            String line;
            while ((line = buf.readLine()) != null) {
                commands.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден! Возможно, вы забыли добавить .txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return commands;
    }
}
