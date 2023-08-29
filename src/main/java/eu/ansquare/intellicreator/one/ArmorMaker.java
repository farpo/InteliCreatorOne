package eu.ansquare.intellicreator.one;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ArmorMaker {
    public static void createArmor(String name, boolean hasHelmet, boolean hasChestplate, boolean hasLeggings, boolean hasBoots,
                                   File texture, @Nullable File helmetTexture, @Nullable File chestTexture, @Nullable File leggingsTexture, @Nullable File bootsTexture,
                                   @Nullable String helmetName, @Nullable String chestName, @Nullable String leggingsName, @Nullable String bootsName,
                                   String itemGroup, String durability, String protection){
        String id = name.toLowerCase().replace(" ", "_").replace("-", "_");
        createArmorClass(id, durability, protection);
        copyTextureFile(id, texture.getPath());
    }
    private static void createArmorClass(String id, String durability, String protection){
        File blockModelFile = new File(Main.getArmorDirectoryPath() + id + ".java");
        blockModelFile.getParentFile().mkdirs();
        System.out.println(blockModelFile.getAbsolutePath());
        try {
            FileWriter myWriter = new FileWriter(blockModelFile.getAbsoluteFile());
            myWriter.write(Templates.parseArmorClass(id, durability, protection));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void copyTextureFile(String name, String texturePath) {
        File textureFileParent = new File(Main.getArmorTexturePath() + name + ".png").getParentFile();
        textureFileParent.mkdirs();
        try {
            Files.copy(Path.of(texturePath).toAbsolutePath(), Path.of(Main.getArmorTexturePath() + name + ".png").toAbsolutePath());
        } catch (IOException e) {
            if (e instanceof FileAlreadyExistsException) {
                System.out.println("Armor texture already exists");
            } else {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}
