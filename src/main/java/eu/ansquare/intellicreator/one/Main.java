package eu.ansquare.intellicreator.one;

import eu.ansquare.intellicreator.one.ui.ICApplication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Properties;

public class Main {
    public static ElementManager elementManager;
    public static void main(String[] args) {
        System.out.println("Hello world!");
        elementManager = new ElementManager(getElementPath());
        elementManager.load();
        //ItemMaker.createItem("t", "C:\\Users\\Adam\\Downloads\\homs.png", "INGREDIENTS");
        //BlockMaker.createBlock("n", "C:\\Users\\Adam\\Downloads\\homs.png", "INGREDIENTS");
        ICApplication.run(args);
    }
    public static String getElementPath(){
        return "icone\\elements\\";
    }
    public static String getID(){
        return getProperties().getProperty("modid");
    }
    public static String getPackage(){return getProperties().getProperty("packagename");}
    public static String getAssetsPath(){
        return "src\\main\\resources\\assets\\" + getID() + "\\";
    }
    public static String getDataPath(){
        return "src\\main\\resources\\data\\" + getID() + "\\";
    }
    public static String getArmorTexturePath(){return "src\\main\\resources\\assets\\minecraft\\textures\\models\\armor\\";}
    public static Properties getProperties(){
        Properties props = new Properties();
        try {
            InputStream stream = Files.newInputStream(Path.of("icone\\icone.properties").toAbsolutePath(), new OpenOption[]{});
            props.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static String getItemClassPath(){
        return "src\\main\\java\\eu\\ansquare\\" + getProperties().getProperty("packagename") + "\\items\\" + getProperties().getProperty("itemclass");
    }
    public static String getBlockClassPath(){
        return "src\\main\\java\\eu\\ansquare\\" + getProperties().getProperty("packagename") + "\\blocks\\" + getProperties().getProperty("blockclass");
    }
    public static String getArmorDirectoryPath(){
        return "src\\main\\java\\eu\\ansquare\\" + getProperties().getProperty("packagename") + "\\items\\";
    }
}