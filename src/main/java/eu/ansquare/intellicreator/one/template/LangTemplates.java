package eu.ansquare.intellicreator.one.template;

import eu.ansquare.intellicreator.one.Main;

public class LangTemplates {
    public static String genBlockEntry(String id, String name){
        String t = ",\"block.%MODID.%ID\" : \"%NAME\"";
        return t.replace("%MODID", Main.getID()).replace("%ID", id).replace("%NAME", name);
    }
    public static String genItemEntry(String id, String name){
        String t = ",\"item.%MODID.%ID\" : \"%NAME\"";
        return t.replace("%MODID", Main.getID()).replace("%ID", id).replace("%NAME", name);
    }
}
