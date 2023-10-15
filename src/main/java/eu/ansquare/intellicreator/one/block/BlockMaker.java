package eu.ansquare.intellicreator.one.block;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.lang.LangCreator;
import eu.ansquare.intellicreator.one.Main;
import eu.ansquare.intellicreator.one.Templates;
import eu.ansquare.intellicreator.one.template.BlockTemplates;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class BlockMaker {
    public static void createBlock(String name, String texturePath, String itemGroup, @Nullable File model) {
//        String id = name.toLowerCase().replace(" ", "_").replace("-", "_");
//        if(model == null){
//            generateBlockModel(id);
//        } else {
//            copyModel(id, model.getPath());
//        }
//        generateBlockstate(id);
//        generateBlockItemModel(id);
//        generateLootTable(id);
//        copyFile(id, texturePath);
//        addBlockField(id, itemGroup);
//        addToLangFile(id, name);
//        LangCreator.addBlockToLang(name, id);
    }
    public static void writeBlockElement(BlockElement element, boolean hasDefaultField){
        generateBlockstate(element.ID);
        generateBlockItemModel(element.ID);
        generateLootTable(element.ID, Main.getID(), element.ID);
        copyTextureFile(element.ID, element.texture);
        if(element.model.equalsIgnoreCase("default")){
            generateBlockModel(element.ID);
        } else{
            copyModelFile(element.ID, element.model);
        }

    }
    private static void generateBlockModel(String id) {
        File blockModelFile = new File(Main.getAssetsPath() + "models\\block\\" + id + ".json");
        blockModelFile.getParentFile().mkdirs();
        try {
            Files.writeString(blockModelFile.toPath(), BlockTemplates.genModel(id));
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void generateBlockstate(String id) {
        File blockstateFile = new File(Main.getAssetsPath() + "blockstates\\" + id + ".json");
        blockstateFile.getParentFile().mkdirs();
        try {
            Files.writeString(blockstateFile.toPath(), BlockTemplates.genBlockState(id));
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void generateBlockItemModel(String id) {
        File itemModelFile = new File(Main.getAssetsPath() + "models\\item\\" + id + ".json");
        itemModelFile.getParentFile().mkdirs();
        try {
            Files.writeString(itemModelFile.toPath(), BlockTemplates.genItemModel(id));
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void generateLootTable(String id, String droppedModId, String droppedId) {
        File lootTableFile = new File(Main.getDataPath() + "loot_tables\\blocks\\" + id + ".json");
        lootTableFile.getParentFile().mkdirs();
        try {
            Files.writeString(lootTableFile.toPath(), BlockTemplates.getLootTable(id, droppedId, droppedModId));
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void copyTextureFile(String id, String texturePath){
        File textureFileParent = new File(Main.getAssetsPath() + "textures\\block\\" + id + ".png").getParentFile();
        textureFileParent.mkdirs();
        try {
            Files.copy(Path.of(texturePath).toAbsolutePath(), Path.of(Main.getAssetsPath() + "textures\\block\\" + id + ".png").toAbsolutePath());
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
    private static void copyModelFile(String id, String modelPath){
        File textureFileParent = new File(Main.getAssetsPath() + "models\\block\\" + id + ".json").getParentFile();
        textureFileParent.mkdirs();
        try {
            Files.copy(Path.of(modelPath).toAbsolutePath(), Path.of(Main.getAssetsPath() + "models\\block\\" + id + ".json").toAbsolutePath());
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
