package eu.ansquare.intellicreator.one;

public class Templates {
    public static final String itemModelTemplate = "{\"parent\": \"minecraft:item/generated\",\"textures\": {\"layer0\": \"%%MODID%%:item/%%ITEMNAME%%\"}}";
    public static final String blockItemModelTemplate = "{\"parent\": \"%%MODID%%:block/%%ITEMNAME%%\"}";
    public static final String blockModelTemplate = "{\n" +
            "  \"parent\": \"block/cube_all\",\n" +
            "  \"textures\": {\n" +
            "    \"all\": \"%%MODID%%:block/%%ITEMNAME%%\"\n" +
            "  }\n" +
            "}";
    public static final String blockstateTemplate = "{\n" +
            "  \"variants\": {\n" +
            "    \"\": { \"model\": \"%%MODID%%:block/%%ITEMNAME%%\" }\n" +
            "  }\n" +
            "}";
    public static final String lootTableTemplate = "{\n" +
            "  \"type\": \"minecraft:block\",\n" +
            "  \"pools\": [\n" +
            "    {\n" +
            "      \"rolls\": 1,\n" +
            "      \"entries\": [\n" +
            "        {\n" +
            "          \"type\": \"minecraft:item\",\n" +
            "          \"name\": \"%%MODID%%:%%ITEMNAME%%\"\n" +
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
    public static final String itemFieldTemplate = "    public static final Item %%UPITEMNAME%% = createItem(\"%%ITEMNAME%%\", new Item(new QuiltItemSettings()), ItemGroups.%%ITEMGROUP%%); ";
    public static final String blockFieldTemplate = "    public static final Block %%UPITEMNAME%% = createBlockAndItem(\"%%ITEMNAME%%\", new Block(QuiltBlockSettings.create()), ItemGroups.%%ITEMGROUP%%); ";
    public static final String blockLangTemplate = ",\"block.%%MODID%%.%%ITEMNAME%%\" : \"%%LANGNAME%%\"";
    public static final String itemLangTemplate = ",\"item.%%MODID%%.%%ITEMNAME%%\" : \"%%LANGNAME%%\"";

    public static String parseStringForFile(String string, String name){
        String newString = string.replace("%%MODID%%", Main.getID()).replace("%%ITEMNAME%%", name).replace("%%UPITEMNAME%%", name.toUpperCase());
        return newString;
    }
    public static String parseLangString(String string, String name, String langName){
        String newString = string.replace("%%MODID%%", Main.getID()).replace("%%ITEMNAME%%", name).replace("%%LANGNAME%%", langName);
        return newString;
    }
    public static String parseBlockSettingsForFile(String string, String itemGroup){
        String newString = string.replace("%%ITEMGROUP%%", itemGroup);
        return newString;
    }
    public static String parseItemSettingsForFile(String string, String itemGroup){
        String newString = string.replace("%%ITEMGROUP%%", itemGroup);
        return newString;
    }
}