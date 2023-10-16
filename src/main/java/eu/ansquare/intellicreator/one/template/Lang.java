package eu.ansquare.intellicreator.one.template;

import eu.ansquare.intellicreator.one.Main;

import java.io.*;
import java.util.LinkedList;

public class Lang {
    public static String genBlockEntry(String id, String name){
        String t = ",\"block.%MODID.%ID\" : \"%NAME\"";
        return t.replace("%MODID", Main.getID()).replace("%ID", id).replace("%NAME", name);
    }
    public static String genItemEntry(String id, String name){
        String t = ",\"item.%MODID.%ID\" : \"%NAME\"";
        return t.replace("%MODID", Main.getID()).replace("%ID", id).replace("%NAME", name);
    }
    public static void block(String id, String name){
        LinkedList<String> lines = new LinkedList<>();
        String blockLangString = Lang.genBlockEntry(id, name);
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
        lines.add(placeToAdd, blockLangString);

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
    public static void item(String id, String name){
        LinkedList<String> lines = new LinkedList<>();
        String itemLangString = Lang.genItemEntry(id, name);
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
        lines.add(placeToAdd, itemLangString);

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
