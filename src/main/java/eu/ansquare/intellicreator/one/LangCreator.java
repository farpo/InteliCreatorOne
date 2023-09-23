package eu.ansquare.intellicreator.one;

import java.io.*;
import java.util.LinkedList;

public class LangCreator {
    public static void addItemToLang(String name, String id){
        LinkedList<String> lines = new LinkedList<>();
        String langString = Templates.parseLangString(Templates.itemLangTemplate, id, name);
        String line;
        File langFile = new File(Main.getAssetsPath() + "lang\\en_us.json");
        try (FileInputStream inputStream = new FileInputStream(langFile)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                inputStream.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int placeToAdd = lines.size() - 2;
        lines.add(placeToAdd, langString);
        try {
            FileWriter myWriter = new FileWriter(langFile.getAbsoluteFile());
            for(String writtenLine : lines){
                myWriter.write(writtenLine);
                myWriter.write(System.lineSeparator());
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void addBlockToLang(String name, String id){
        LinkedList<String> lines = new LinkedList<>();
        String langString = Templates.parseLangString(Templates.blockLangTemplate, id, name);
        String line;
        File langFile = new File(Main.getAssetsPath() + "lang\\en_us.json");
        try (FileInputStream inputStream = new FileInputStream(langFile)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                inputStream.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int placeToAdd = lines.size() - 2;
        lines.add(placeToAdd, langString);
        try {
            FileWriter myWriter = new FileWriter(langFile.getAbsoluteFile());
            for(String writtenLine : lines){
                myWriter.write(writtenLine);
                myWriter.write(System.lineSeparator());
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
