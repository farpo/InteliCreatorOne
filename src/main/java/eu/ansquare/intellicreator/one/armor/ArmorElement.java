package eu.ansquare.intellicreator.one.armor;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.Main;
import eu.ansquare.intellicreator.one.item.ItemElement;
import eu.ansquare.intellicreator.one.item.ItemMaker;
import eu.ansquare.intellicreator.one.util.CollectionUtils;

import java.io.File;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class ArmorElement extends Element {
    private final String[] keys = new String[]{"name", "texture", "texture2", "itemgroup","durability","protection", "helmet", "chestplate","leggings", "boots"};

    public String name;
    public int durability;
    public int protection;
    public String texture;
    public String texture2;

    public Element.ItemGroup itemGroup;

    public ItemElement helmet;
    public ItemElement chestplate;
    public ItemElement leggings;
    public ItemElement boots;

    public ArmorElement(String id) {
        super(id);
    }
    public ArmorElement name(String name){
        this.name = name;
        return this;
    }
    public ArmorElement durability(int durability){
        this.durability = durability;
        return this;
    }
    public ArmorElement protection(int protection){
        this.protection = protection;
        return this;
    }
    public ArmorElement texture(File texture, File secondTexture){
        this.texture = texture.getAbsolutePath();
        this.texture2 = secondTexture.getAbsolutePath();
        return this;
    }

    public ArmorElement group(Element.ItemGroup group){
        this.itemGroup = group;
        return this;
    }
    public ArmorElement helmet(ItemElement helmet){
        this.helmet = helmet;
        this.helmet.group(this.itemGroup);
        return this;
    }
    public ArmorElement chestplate(ItemElement chestplate){
        this.chestplate = chestplate;
        this.chestplate.group(this.itemGroup);
        return this;
    }
    public ArmorElement leggings(ItemElement leggings){
        this.leggings = leggings;
        this.leggings.group(this.itemGroup);
        return this;
    }
    public ArmorElement boots(ItemElement boots){
        this.boots = boots;
        this.boots.group(this.itemGroup);
        return this;
    }
    @Override
    public String icon() {
        return null;
    }

    @Override
    public Map<String, String> toFile() {
        String type = "armor";
        ArmorPieceWriter armorPieceWriter = Main.elementManager.armorPieceWriter;
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        CollectionUtils.putAll(map, keys, name, texture, texture2, itemGroup.key(), String.valueOf(durability), String.valueOf(protection),
                armorPieceWriter.getString(helmet),
                armorPieceWriter.getString(chestplate),
                armorPieceWriter.getString(leggings),
                armorPieceWriter.getString(boots));
        return map;
    }

    @Override
    public Element fromFile(Map<String, String> inputMap) {
        if(CollectionUtils.containsKeys(inputMap, keys)){
            name = inputMap.get("name");
            durability = Integer.parseInt(inputMap.get("durability"));
            protection = Integer.parseInt(inputMap.get("protection"));
            helmet = Main.elementManager.armorPieceWriter.fromString(inputMap.get("helmet"));
            chestplate = Main.elementManager.armorPieceWriter.fromString(inputMap.get("chestplate"));
            leggings = Main.elementManager.armorPieceWriter.fromString(inputMap.get("leggings"));
            boots = Main.elementManager.armorPieceWriter.fromString(inputMap.get("boots"));
            texture = inputMap.get("texture");
            texture2 = inputMap.get("texture2");
            itemGroup = ItemGroup.valueOf(inputMap.get("itemgroup").toUpperCase());
            return this;
        } else {
            throw new RuntimeException("Element misformed");
        }
    }

    @Override
    public void write() {
        ArmorMaker.writeArmorElement(this);
        ArmorMaker.writeArmorItemElement(helmet, this, "helmet");
        ArmorMaker.writeArmorItemElement(chestplate, this, "chestplate");
        ArmorMaker.writeArmorItemElement(leggings, this, "leggings");
        ArmorMaker.writeArmorItemElement(boots, this, "boots");
    }
}
