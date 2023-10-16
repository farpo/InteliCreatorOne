package eu.ansquare.intellicreator.one.item;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.block.BlockElement;
import eu.ansquare.intellicreator.one.block.BlockMaker;
import eu.ansquare.intellicreator.one.util.CollectionUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ItemElement extends Element {
    public String name;
    public String texture;
    public String model;
    public Element.ItemGroup itemGroup;
    private final String[] keys = new String[]{"name", "texture", "model", "itemgroup"};

    public ItemElement(String id) {
        super(id);
    }
    public ItemElement name(String name){
        this.name = name;
        return this;
    }
    public ItemElement texture(File texture){
        this.texture = texture.getAbsolutePath();
        return this;
    }
    public ItemElement model(File model){
        if(model == null){
            this.model = "default";
        } else {
            this.model = model.getAbsolutePath();
        }
        return this;
    }
    public ItemElement group(Element.ItemGroup group){
        this.itemGroup = group;
        return this;
    }
    public String icon() {
        return null;
    }

    @Override
    public Map<String, String> toFile() {
        String type = "item";
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        CollectionUtils.putAll(map, keys, name, texture, model, itemGroup.key());
        return map;
    }

    @Override
    public Element fromFile(Map<String, String> inputMap) {
        if(CollectionUtils.containsKeys(inputMap, "name")){
            name = inputMap.get("name");
            texture = inputMap.get("texture");
            model = inputMap.get("model");
            itemGroup = ItemGroup.valueOf(inputMap.get("itemgroup").toUpperCase());
            return this;
        } else {
            throw new RuntimeException("Element misformed");
        }
    }

    @Override
    public void write() {
        ItemMaker.writeItemElement(this, true);
    }
}
