package eu.ansquare.intellicreator.one;

import eu.ansquare.intellicreator.one.ui.ICApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        //ItemMaker.createItem("t", "C:\\Users\\Adam\\Downloads\\homs.png", "INGREDIENTS");
        //BlockMaker.createBlock("n", "C:\\Users\\Adam\\Downloads\\homs.png", "INGREDIENTS");
        //ICApplication.launch(args);
        ICApplication.run(args);
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
        InputStream stream = Main.class.getResourceAsStream("/icone.properties");
        Properties props = new Properties();
        try {
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