package eu.ansquare.intellicreator.one.armor;

import eu.ansquare.intellicreator.one.item.ItemMaker;
import eu.ansquare.intellicreator.one.Main;
import eu.ansquare.intellicreator.one.Templates;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class ArmorMaker {
    public static void createArmor(String name, boolean hasHelmet, boolean hasChestplate, boolean hasLeggings, boolean hasBoots,
                                   File texture, File layer, @Nullable File helmetTexture, @Nullable File chestTexture, @Nullable File leggingsTexture, @Nullable File bootsTexture,
                                   @Nullable String helmetName, @Nullable String chestName, @Nullable String leggingsName, @Nullable String bootsName,
                                   String itemGroup, String durability, String protection){
        String id = name.toLowerCase().replace(" ", "_").replace("-", "_");
        String className = Templates.capital(id).replace("_", "");
        createArmorClass(className, id, durability, protection);
        copyTextureFile(id, texture.getPath());
        copyLayer(id, layer.getPath());
        addArmorMatField(id, className);
        if(hasHelmet){
            String helmetId = id + "_helmet";
            ItemMaker.createArmorItem(helmetName, helmetId, helmetTexture.getPath(), itemGroup.toUpperCase(), "HELMET", id);
        }
        if(hasChestplate){
            String chestId = id + "_chestplate";
            ItemMaker.createArmorItem(chestName, chestId, chestTexture.getPath(), itemGroup.toUpperCase(), "CHESTPLATE", id);
        }
        if(hasLeggings){
            String leggingsId = id + "_leggings";
            ItemMaker.createArmorItem(leggingsName, leggingsId, leggingsTexture.getPath(), itemGroup.toUpperCase(), "LEGGINGS", id);
        }
        if(hasBoots){
            String bootId = id + "_boots";
            ItemMaker.createArmorItem(bootsName, bootId, bootsTexture.getPath(), itemGroup.toUpperCase(), "BOOTS", id);
        }
    }
    private static void createArmorClass(String classnameString, String id, String durability, String protection){
        String className = classnameString + "Material.java";
        File armorClassFile = new File(Main.getArmorDirectoryPath() + className);
        armorClassFile.getParentFile().mkdirs();
        System.out.println(armorClassFile.getAbsolutePath());
        try {
            FileWriter myWriter = new FileWriter(armorClassFile.getAbsoluteFile());
            myWriter.write(Templates.parseArmorClass(classnameString, id, durability, protection));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void copyTextureFile(String name, String texturePath) {
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
    }
    private static void copyLayer(String name, String texturePath) {
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
    }
    private static void addArmorMatField(String id, String classname) {
        LinkedList<String> lines = new LinkedList<>();
        String armorMatField = Templates.parseArmorMatField(id, classname);
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
