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
    public static final String armorMaterialFieldTemplate = "    public static final ArmorMaterial %%MATERIAL%% = new %%MATERIALCLASS%%(); ";
    public static final String itemFieldTemplate = "    public static final Item %%UPITEMNAME%% = createItem(\"%%ITEMNAME%%\", new Item(new QuiltItemSettings()), ItemGroups.%%ITEMGROUP%%); ";
    public static final String blockFieldTemplate = "    public static final Block %%UPITEMNAME%% = createBlockAndItem(\"%%ITEMNAME%%\", new Block(QuiltBlockSettings.create()), ItemGroups.%%ITEMGROUP%%); ";
    public static final String armorItemFieldTemplate = "    public static final Item %%UPITEMNAME%% = createItem(\"%%ITEMNAME%%\", new ArmorItem(%%MATERIAL%%, ArmorItem.ArmorSlot.%%SLOT%%, new QuiltItemSettings().maxCount(1)), ItemGroups.%%ITEMGROUP%%); ";
    public static final String blockLangTemplate = ",\"block.%%MODID%%.%%ITEMNAME%%\" : \"%%LANGNAME%%\"";
    public static final String itemLangTemplate = ",\"item.%%MODID%%.%%ITEMNAME%%\" : \"%%LANGNAME%%\"";
    public static final String armorClassTemplate = "package eu.ansquare.%%PACKAGENAME%%.items;\n" +
            "\n" +
            "import net.minecraft.item.ArmorItem;\n" +
            "import net.minecraft.item.ArmorMaterial;\n" +
            "import net.minecraft.item.Items;\n" +
            "import net.minecraft.recipe.Ingredient;\n" +
            "import net.minecraft.sound.SoundEvent;\n" +
            "import net.minecraft.sound.SoundEvents;\n" +
            "\n" +
            "public class %%NAME%% implements ArmorMaterial {\n" +
            "\t@Override\n" +
            "\tpublic int getDurability(ArmorItem.ArmorSlot slot) {\n" +
            "\t\treturn %%DURAB%%;\n" +
            "\t}\n" +
            "\n" +
            "\t@Override\n" +
            "\tpublic int getProtection(ArmorItem.ArmorSlot slot) {\n" +
            "\t\treturn %%PROT%%;\n" +
            "\t}\n" +
            "\n" +
            "\t@Override\n" +
            "\tpublic int getEnchantability() {\n" +
            "\t\treturn 1;\n" +
            "\t}\n" +
            "\n" +
            "\t@Override\n" +
            "\tpublic SoundEvent getEquipSound() {\n" +
            "\t\treturn SoundEvents.ITEM_ARMOR_EQUIP_CHAIN;\n" +
            "\t}\n" +
            "\n" +
            "\t@Override\n" +
            "\tpublic Ingredient getRepairIngredient() {\n" +
            "\t\treturn Ingredient.ofItems(Items.DIAMOND);\n" +
            "\t}\n" +
            "\n" +
            "\t@Override\n" +
            "\tpublic String getName() {\n" +
            "\t\treturn \"%%ARMORNAME%%\";\n" +
            "\t}\n" +
            "\n" +
            "\t@Override\n" +
            "\tpublic float getToughness() {\n" +
            "\t\treturn 0;\n" +
            "\t}\n" +
            "\n" +
            "\t@Override\n" +
            "\tpublic float getKnockbackResistance() {\n" +
            "\t\treturn 0;\n" +
            "\t}\n" +
            "}\n";

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
    public static String parseArmorClass(String classname, String id, String durab, String prot){
        String newString = armorClassTemplate.replace("%%NAME%%", capital(classname) + "Material").replace("%%DURAB%%", durab).replace("%%PROT%%", prot).replace("%%PACKAGENAME%%", Main.getPackage()).replace("%%ARMORNAME%%", id);
        return newString;
    }
    public static String parseArmorMatField(String id, String className){
        String newString = armorMaterialFieldTemplate.replace("%%MATERIAL%%", id.toUpperCase()).replace("%%MATERIALCLASS%%", className + "Material");
        return newString;
    }
    public static String parseArmorItemField(String id, String itemGroup, String slot, String material){
        String newString = armorItemFieldTemplate.replace("%%UPITEMNAME%%", id.toUpperCase()).replace("%%ITEMNAME%%", id).replace("%%ITEMGROUP%%", itemGroup).replace("%%SLOT%%", slot).replace("%%MATERIAL%%", material);
        return newString;
    }
    public static String capital(String oldString){
        String newString = oldString.substring(0, 1).toUpperCase() + oldString.substring(1);
        return newString;
    }
}