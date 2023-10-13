package eu.ansquare.intellicreator.one.block;

import eu.ansquare.intellicreator.one.lang.LangCreator;
import eu.ansquare.intellicreator.one.Main;
import eu.ansquare.intellicreator.one.Templates;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class BlockMaker {
    public static void createBlock(String name, String texturePath, String itemGroup, @Nullable File model) {
        String id = name.toLowerCase().replace(" ", "_").replace("-", "_");
        if(model == null){
            generateBlockModel(id);
        } else {
            copyModel(id, model.getPath());
        }
        generateBlockstate(id);
        generateBlockItemModel(id);
        generateLootTable(id);
        copyFile(id, texturePath);
        addBlockField(id, itemGroup);
        addToLangFile(id, name);
        LangCreator.addBlockToLang(name, id);
    }
    private static void generateBlockModel(String name) {
        File blockModelFile = new File(Main.getAssetsPath() + "models\\block\\" + name + ".json");
        blockModelFile.getParentFile().mkdirs();
        System.out.println(blockModelFile.getAbsolutePath());
        try {
            FileWriter myWriter = new FileWriter(blockModelFile.getAbsoluteFile());
            myWriter.write(Templates.parseStringForFile(Templates.blockModelTemplate, name));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void generateBlockstate(String name) {
        File blockstateFile = new File(Main.getAssetsPath() + "blockstates\\" + name + ".json");
        blockstateFile.getParentFile().mkdirs();
        System.out.println(blockstateFile.getAbsolutePath());
        try {
            FileWriter myWriter = new FileWriter(blockstateFile.getAbsoluteFile());
            myWriter.write(Templates.parseStringForFile(Templates.blockstateTemplate, name));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void generateBlockItemModel(String name) {
        File itemModelFile = new File(Main.getAssetsPath() + "models\\item\\" + name + ".json");
        itemModelFile.getParentFile().mkdirs();
        System.out.println(itemModelFile.getAbsolutePath());
        try {
            FileWriter myWriter = new FileWriter(itemModelFile.getAbsoluteFile());
            myWriter.write(Templates.parseStringForFile(Templates.blockItemModelTemplate, name));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void generateLootTable(String name) {
        File lootTableFile = new File(Main.getDataPath() + "loot_tables\\blocks\\" + name + ".json");
        lootTableFile.getParentFile().mkdirs();
        System.out.println(lootTableFile.getAbsolutePath());
        try {
            FileWriter myWriter = new FileWriter(lootTableFile.getAbsoluteFile());
            myWriter.write(Templates.parseStringForFile(Templates.lootTableTemplate, name));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void copyFile(String name, String texturePath){
        File textureFileParent = new File(Main.getAssetsPath() + "textures\\block\\" + name + ".png").getParentFile();
        textureFileParent.mkdirs();
        try {
            Files.copy(Path.of(texturePath).toAbsolutePath(), Path.of(Main.getAssetsPath() + "textures\\block\\" + name + ".png").toAbsolutePath());
        } catch (IOException e) {
            if(e instanceof FileAlreadyExistsException){
                System.out.println("Block texture already exists");
            }
            else {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
    private static void copyModel(String name, String modelPath){
        File textureFileParent = new File(Main.getAssetsPath() + "models\\block\\" + name + ".json").getParentFile();
        textureFileParent.mkdirs();
        try {
            Files.copy(Path.of(modelPath).toAbsolutePath(), Path.of(Main.getAssetsPath() + "models\\block\\" + name + ".json").toAbsolutePath());
        } catch (IOException e) {
            if(e instanceof FileAlreadyExistsException){
                System.out.println("Block model already exists");
            }
            else {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
    private static void addToLangFile(String name, String langName){
        LinkedList<String> lines = new LinkedList<>();
        String langString = Templates.parseLangString(Templates.blockLangTemplate, name, langName);
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
    private static void addBlockField(String name, String itemgroup) {
        LinkedList<String> lines = new LinkedList<>();
        String unmodifiedBlockString = Templates.parseStringForFile(Templates.blockFieldTemplate, name);
        String modifedBlockString = Templates.parseBlockSettingsForFile(unmodifiedBlockString, itemgroup);
        String line;
        File itemClassFile = new File(Main.getBlockClassPath());
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
        lines.add(placeToAdd, modifedBlockString);
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
