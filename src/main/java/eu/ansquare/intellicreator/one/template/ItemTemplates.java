package eu.ansquare.intellicreator.one.template;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.Main;

public class ItemTemplates {
    public static String genItemModel(String id){
        String t = "{\"parent\": \"minecraft:item/generated\",\"textures\": {\"layer0\": \"%MODID:item/%ID\"}}";
        return t.replace("%MODID", Main.getID()).replace("%ID", id);
    }
    public static String genSimpleItemField(String id, Element.ItemGroup itemGroup, int maxCount){
        String t = "    public static final Item %UPID = %METHOD(\"%ID\", new Item(new QuiltItemSettings().maxCount(%MAX), ItemGroups.%ITEMGROUP); ";
        return t.replace("%UPID", id.toUpperCase()).replace("%ID", id).replace("%ITEMGROUP", itemGroup.key().toUpperCase()).replace("%METHOD", Main.getProperties().getProperty("itemmethod")).replace("%MAX", String.valueOf(maxCount));
    }
    public static String genHeadItemField(String id, Element.ItemGroup itemGroup, int maxCount){
        String t = "    public static final Item %UPID = %METHOD(\"%ID\", new Item(new QuiltItemSettings().maxCount(%MAX).equipmentSlot(EquipmentSlot.HEAD), ItemGroups.%ITEMGROUP); ";
        return t.replace("%UPID", id.toUpperCase()).replace("%ID", id).replace("%ITEMGROUP", itemGroup.key().toUpperCase()).replace("%METHOD", Main.getProperties().getProperty("itemmethod")).replace("%MAX", String.valueOf(maxCount));
    }
}
