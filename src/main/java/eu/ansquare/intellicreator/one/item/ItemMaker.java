package eu.ansquare.intellicreator.one.item;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.Main;
import eu.ansquare.intellicreator.one.Templates;
import eu.ansquare.intellicreator.one.template.BlockTemplates;
import eu.ansquare.intellicreator.one.template.ItemTemplates;
import eu.ansquare.intellicreator.one.template.Lang;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class ItemMaker {

    public static void writeItemElement(ItemElement element, boolean hasDefaultField){
        element.texture(copyTextureFile(element.ID, element.texture));
        if (element.model == null){
            element.model(generateItemModel(element.ID));
        }
        else if(element.model.equalsIgnoreCase("default")){
            element.model(generateItemModel(element.ID));
        } else{
            element.model(copyModelFile(element.ID, element.model));
        }
        Lang.item(element.ID, element.name);
        if(hasDefaultField){
            addSimpleItemField(element.ID, element.itemGroup);
        }
    }

    private static File copyTextureFile(String id, String texturePath){
        File textureFileParent = new File(Main.getAssetsPath() + "textures\\item\\" + id + ".png").getParentFile();
        textureFileParent.mkdirs();
        try {
            Files.copy(Path.of(texturePath).toAbsolutePath(), Path.of(Main.getAssetsPath() + "textures\\item\\" + id + ".png").toAbsolutePath());
        } catch (IOException e) {
            if(e instanceof FileAlreadyExistsException){
                System.out.println("Item texture already exists");
            }
            else {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return new File(Main.getAssetsPath() + "textures\\item\\" + id + ".png");
    }
    private static File generateItemModel(String id) {
        File itemModelFile = new File(Main.getAssetsPath() + "models\\item\\" + id + ".json");
        itemModelFile.getParentFile().mkdirs();
        try {
            Files.writeString(itemModelFile.toPath(), ItemTemplates.genItemModel(id));
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return itemModelFile;
    }
    private static File copyModelFile(String id, String texturePath){
        File textureFileParent = new File(Main.getAssetsPath() + "models\\item\\" + id + ".json").getParentFile();
        textureFileParent.mkdirs();
        try {
            Files.copy(Path.of(texturePath).toAbsolutePath(), Path.of(Main.getAssetsPath() + "models\\item\\" + id + ".json").toAbsolutePath());
        } catch (IOException e) {
            if(e instanceof FileAlreadyExistsException){
                System.out.println("Item model already exists");
            }
            else {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return new File(Main.getAssetsPath() + "models\\item\\" + id + ".json");
    }

    private static void addSimpleItemField(String id, Element.ItemGroup itemgroup) {
        LinkedList<String> lines = new LinkedList<>();
        String field = ItemTemplates.genSimpleItemField(id, itemgroup);
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
        lines.add(placeToAdd, field);
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
