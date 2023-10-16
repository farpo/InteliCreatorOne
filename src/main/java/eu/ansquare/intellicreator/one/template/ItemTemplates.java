package eu.ansquare.intellicreator.one.template;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.Main;

public class ItemTemplates {
    public static String genItemModel(String id){
        String t = "{\"parent\": \"minecraft:item/generated\",\"textures\": {\"layer0\": \"%MODID:item/%ID\"}}";
        return t.replace("%MODID", Main.getID()).replace("%ID", id);
    }
    public static String genSimpleItemField(String id, Element.ItemGroup itemGroup){
        String t = "    public static final Item %UPID = %METHOD(\"%ID\", new Item(new QuiltItemSettings()), ItemGroups.%ITEMGROUP); ";
        return t.replace("%UPID", id.toUpperCase()).replace("%ID", id).replace("%ITEMGROUP", itemGroup.key()).replace("%METHOD", Main.getProperties().getProperty("itemmethod"));
    }
}
