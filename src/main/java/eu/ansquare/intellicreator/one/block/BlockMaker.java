package eu.ansquare.intellicreator.one.block;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.Main;
import eu.ansquare.intellicreator.one.template.BlockTemplates;
import eu.ansquare.intellicreator.one.template.Lang;
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
        element.texture(copyTextureFile(element.ID, element.texture));
        if(element.model.equalsIgnoreCase("default")){
            element.model(generateBlockModel(element.ID));
        } else{
            element.model(copyModelFile(element.ID, element.model));
        }
        Lang.block(element.ID, element.name);
        Lang.item(element.ID, element.name);
        addSimpleBlockField(element.ID, element.itemGroup);
    }
    private static File generateBlockModel(String id) {
        File blockModelFile = new File(Main.getAssetsPath() + "models\\block\\" + id + ".json");
        blockModelFile.getParentFile().mkdirs();
        try {
            Files.writeString(blockModelFile.toPath(), BlockTemplates.genModel(id));
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return blockModelFile;
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
    private static File copyTextureFile(String id, String texturePath){
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
        return new File(Main.getAssetsPath() + "textures\\block\\" + id + ".png");
    }
    private static File copyModelFile(String id, String modelPath){
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
        return new File(Main.getAssetsPath() + "models\\block\\" + id + ".json");
    }
    private static void addSimpleBlockField(String id, Element.ItemGroup itemgroup) {
        LinkedList<String> lines = new LinkedList<>();
        String field = BlockTemplates.genSimpleBlockField(id, itemgroup);
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
}
