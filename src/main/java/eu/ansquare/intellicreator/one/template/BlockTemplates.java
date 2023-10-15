package eu.ansquare.intellicreator.one.template;

import eu.ansquare.intellicreator.one.Main;

public class BlockTemplates {
    public static String genModel(String id){
        String t = "{\n" +
                "  \"parent\": \"block/cube_all\",\n" +
                "  \"textures\": {\n" +
                "    \"all\": \"%MODID:block/%ID\"\n" +
                "  }\n" +
                "}";
        return t.replace("%ID", id).replace("%MODID", Main.getID());
    }
    public static String genBlockState(String id){
        String t = "{\n" +
                "  \"variants\": {\n" +
                "    \"\": { \"model\": \"%MODID:block/%ID\" }\n" +
                "  }\n" +
                "}";
        return t.replace("%ID", id).replace("%MODID", Main.getID());

    }
    public static String genItemModel(String id){
        String t = "{\"parent\": \"%MODID:block/%ID\"}";;
        t.replace("%ID", id).replace("%MODID", Main.getID());
        return t;
    }
    public static String getLootTable(String id, String droppedId, String droppedModId){
        String t ="{\n" +
                "  \"type\": \"minecraft:block\",\n" +
                "  \"pools\": [\n" +
                "    {\n" +
                "      \"rolls\": 1,\n" +
                "      \"entries\": [\n" +
                "        {\n" +
                "          \"type\": \"minecraft:item\",\n" +
                "          \"name\": \"%DROPMODID:%DROPID\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"conditions\": [\n" +
                "        {\n" +
                "          \"condition\": \"minecraft:survives_explosion\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        return t.replace("%DROPID", droppedId).replace("%DROPMODID", droppedModId);
    }
}
