package eu.ansquare.intellicreator.one.item;

import eu.ansquare.intellicreator.one.lang.LangCreator;
import eu.ansquare.intellicreator.one.Main;
import eu.ansquare.intellicreator.one.Templates;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class ItemMaker {
    public static void createItem(String name, String texturePath, String itemGroup, @Nullable File model) {
        String id = name.toLowerCase().replace(" ", "_").replace("-", "_");
        if(model == null){
            generateItemModel(id);
        } else {
            copyModel(id, model.getPath());
        }
        copyFile(id, texturePath);
        addItemField(id, itemGroup);
        LangCreator.addItemToLang(name, id);
    }
    public static void createArmorItem(String langName, String name, String texturePath, String itemGroup, String slot, String material){
        generateItemModel(name);
        copyFile(name, texturePath);
        LangCreator.addItemToLang(langName, name);
        addArmorItemField(name, itemGroup, slot, material);
    }
    private static void generateItemModel(String name) {
        File itemModelFile = new File(Main.getAssetsPath() + "models\\item\\" + name + ".json");
        itemModelFile.getParentFile().mkdirs();
        System.out.println(itemModelFile.getAbsolutePath());
        try {
            FileWriter myWriter = new FileWriter(itemModelFile.getAbsoluteFile());
            myWriter.write(Templates.parseStringForFile(Templates.itemModelTemplate, name));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void copyModel(String name, String modelPath){
        File modelFileParent = new File(Main.getAssetsPath() + "models\\item\\" + name + ".json").getParentFile();
        modelFileParent.mkdirs();
        try {
            Files.copy(Path.of(modelPath).toAbsolutePath(), Path.of(Main.getAssetsPath() + "models\\item\\" + name + ".json").toAbsolutePath());
        } catch (IOException e) {
            if (e instanceof FileAlreadyExistsException) {
                System.out.println("Item model already exists");
            } else {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
    private static void copyFile(String name, String texturePath) {
        File textureFileParent = new File(Main.getAssetsPath() + "textures\\item\\" + name + ".png").getParentFile();
        textureFileParent.mkdirs();
        try {
            Files.copy(Path.of(texturePath).toAbsolutePath(), Path.of(Main.getAssetsPath() + "textures\\item\\" + name + ".png").toAbsolutePath());
        } catch (IOException e) {
            if (e instanceof FileAlreadyExistsException) {
                System.out.println("Item texture already exists");
            } else {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
    private static void addItemField(String name, String itemgroup) {
        LinkedList<String> lines = new LinkedList<>();
        String unmodifiedItemString = Templates.parseStringForFile(Templates.itemFieldTemplate, name);
        String modifedItemString = Templates.parseItemSettingsForFile(unmodifiedItemString, itemgroup);
        String line;
        File itemClassFile = new File(Main.getItemClassPath());
        try (FileInputStream inputStream = new FileInputStream(itemClassFile)) {
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
        lines.add(placeToAdd, modifedItemString);
        try {
            FileWriter myWriter = new FileWriter(itemClassFile.getAbsoluteFile());
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
    private static void addArmorItemField(String name, String itemgroup, String slot, String material) {
        LinkedList<String> lines = new LinkedList<>();
        String armorFieldString = Templates.parseArmorItemField(name, itemgroup, slot, material.toUpperCase());
        String line;
        File itemClassFile = new File(Main.getItemClassPath());
        try (FileInputStream inputStream = new FileInputStream(itemClassFile)) {
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
        lines.add(placeToAdd, armorFieldString);
        try {
            FileWriter myWriter = new FileWriter(itemClassFile.getAbsoluteFile());
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
