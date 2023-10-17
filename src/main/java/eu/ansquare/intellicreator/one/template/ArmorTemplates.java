package eu.ansquare.intellicreator.one.template;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.Main;

public class ArmorTemplates {
    public static String genArmorClass(String durability, String protection, String className, String id){
        String t = "package eu.ansquare.%PACKAGE.items.armor;\n" +
                "\n" +
                "import net.minecraft.item.ArmorItem;\n" +
                "import net.minecraft.item.ArmorMaterial;\n" +
                "import net.minecraft.item.Items;\n" +
                "import net.minecraft.recipe.Ingredient;\n" +
                "import net.minecraft.sound.SoundEvent;\n" +
                "import net.minecraft.sound.SoundEvents;\n" +
                "\n" +
                "public class %NAME implements ArmorMaterial {\n" +
                "\t@Override\n" +
                "\tpublic int getDurability(ArmorItem.ArmorSlot slot) {\n" +
                "\t\treturn %D;\n" +
                "\t}\n" +
                "\n" +
                "\t@Override\n" +
                "\tpublic int getProtection(ArmorItem.ArmorSlot slot) {\n" +
                "\t\treturn %P;\n" +
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
                "\t\treturn \"%ARMORNAME\";\n" +
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
        return t.replace("%PACKAGE", Main.getPackage()).replace("%ARMORNAME", id).replace("%NAME", className).replace("%P", protection).replace("%D", durability);
    }
    public static String genArmorItemField(String id, String slot, Element.ItemGroup itemGroup, String material){
        String t = "    public static final Item %UPID = %METHOD(\"%ID\", new ArmorItem(%MATERIAL, ArmorItem.ArmorSlot.%SLOT, new QuiltItemSettings().maxCount(1)), ItemGroups.%ITEMGROUP); ";
        return t.replace("%UPID", id.toUpperCase()).replace("%ID", id).replace("%MATERIAL", material.toUpperCase()).replace("%MATERIAL", material.toUpperCase()).replace("%SLOT", slot.toUpperCase()).replace("%ITEMGROUP", itemGroup.key().toUpperCase()).replace("%METHOD", Main.getProperties().getProperty("itemmethod"));
    }
    public static String genMatField(String id, String classname){
        String t = "    public static final ArmorMaterial %UPID = new %CLASS(); ";
        return t.replace("%UPID", id.toUpperCase()).replace("%CLASS", classname);
    }
}
