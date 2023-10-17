package eu.ansquare.intellicreator.one.armor;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.item.ItemElement;
import eu.ansquare.intellicreator.one.item.ItemMaker;
import eu.ansquare.intellicreator.one.Main;
import eu.ansquare.intellicreator.one.Templates;
import eu.ansquare.intellicreator.one.template.ArmorTemplates;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class ArmorMaker {
//    public static void createArmor(String name, boolean hasHelmet, boolean hasChestplate, boolean hasLeggings, boolean hasBoots,
//                                   File texture, File layer, @Nullable File helmetTexture, @Nullable File chestTexture, @Nullable File leggingsTexture, @Nullable File bootsTexture,
//                                   @Nullable String helmetName, @Nullable String chestName, @Nullable String leggingsName, @Nullable String bootsName,
//                                   String itemGroup, String durability, String protection){
//        String id = name.toLowerCase().replace(" ", "_").replace("-", "_");
//        String className = Templates.capital(id).replace("_", "");
//        createArmorClass(className, id, durability, protection);
//        copyTextureFile(id, texture.getPath());
//        copyLayer(id, layer.getPath());
//        addArmorMatField(id, className);
//        if(hasHelmet){
//            String helmetId = id + "_helmet";
//            ItemMaker.createArmorItem(helmetName, helmetId, helmetTexture.getPath(), itemGroup.toUpperCase(), "HELMET", id);
//        }
//        if(hasChestplate){
//            String chestId = id + "_chestplate";
//            ItemMaker.createArmorItem(chestName, chestId, chestTexture.getPath(), itemGroup.toUpperCase(), "CHESTPLATE", id);
//        }
//        if(hasLeggings){
//            String leggingsId = id + "_leggings";
//            ItemMaker.createArmorItem(leggingsName, leggingsId, leggingsTexture.getPath(), itemGroup.toUpperCase(), "LEGGINGS", id);
//        }
//        if(hasBoots){
//            String bootId = id + "_boots";
//            ItemMaker.createArmorItem(bootsName, bootId, bootsTexture.getPath(), itemGroup.toUpperCase(), "BOOTS", id);
//        }
//    }
    public static void writeArmorItemElement(ItemElement element, ArmorElement armor, String slot){
        if(element != null){
            ItemMaker.writeItemElement(element, false);
            addArmorItemField(element.ID, element.itemGroup, slot, armor.ID);
        }
    }
    public static void writeArmorElement(ArmorElement element){
        String className = Templates.capital(element.ID) + "Material";
        createArmorClass(className, element.ID, String.valueOf(element.durability), String.valueOf(element.protection));
        element.texture(copyTextureFile(element.ID, element.texture), copyLayerFile(element.ID, element.texture2));
        addArmorMatField(element.ID, className);
    }
    private static void createArmorClass(String classnameString, String id, String durability, String protection){
        String className = classnameString + ".java";
        File armorClassFile = new File(Main.getArmorDirectoryPath() + className);
        armorClassFile.getParentFile().mkdirs();
        System.out.println(armorClassFile.getAbsolutePath());
        try {
            FileWriter myWriter = new FileWriter(armorClassFile.getAbsoluteFile());
            myWriter.write(ArmorTemplates.genArmorClass(durability, protection, classnameString, id));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static File copyTextureFile(String name, String texturePath) {
        File textureFileParent = new File(Main.getArmorTexturePath() + name + "_layer_1.png").getParentFile();
        textureFileParent.mkdirs();
        try {
            Files.copy(Path.of(texturePath).toAbsolutePath(), Path.of(Main.getArmorTexturePath() + name + "_layer_1.png").toAbsolutePath());
        } catch (IOException e) {
            if (e instanceof FileAlreadyExistsException) {
                System.out.println("Armor texture already exists");
            } else {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return new File(Main.getArmorTexturePath() + name + "_layer_1.png");
    }
    private static File copyLayerFile(String name, String texturePath) {
        File textureFileParent = new File(Main.getArmorTexturePath() + name + "_layer_2.png").getParentFile();
        textureFileParent.mkdirs();
        try {
            Files.copy(Path.of(texturePath).toAbsolutePath(), Path.of(Main.getArmorTexturePath() + name + "_layer_2.png").toAbsolutePath());
        } catch (IOException e) {
            if (e instanceof FileAlreadyExistsException) {
                System.out.println("Armor texture already exists");
            } else {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return new File(Main.getArmorTexturePath() + name + "_layer_2.png");
    }
    private static void addArmorItemField(String id, Element.ItemGroup itemgroup, String slot, String material) {
        LinkedList<String> lines = new LinkedList<>();
        String armorFieldString = ArmorTemplates.genArmorItemField(id, slot, itemgroup, material);
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
    private static void addArmorMatField(String id, String classname) {
        LinkedList<String> lines = new LinkedList<>();
        String armorMatField = ArmorTemplates.genMatField(id, classname);
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
        lines.add(placeToAdd, armorMatField);
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
