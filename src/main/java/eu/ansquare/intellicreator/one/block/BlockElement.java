package eu.ansquare.intellicreator.one.block;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

public class BlockElement extends Element {
    public String name;
    public String texture;
    public String model;
    public Element.ItemGroup itemGroup;
    private final String[] keys = new String[]{"name", "texture", "model", "itemgroup"};

    public BlockElement(String id) {
        super(id);
    }
    public BlockElement name(String name){
        this.name = name;
        return this;
    }
    public BlockElement texture(String texture){
        this.texture = texture;
        return this;
    }
    public BlockElement model(String model){
        this.model = model;
        return this;
    }
    public BlockElement group(Element.ItemGroup group){
        this.itemGroup = group;
        return this;
    }
    public String icon() {
        return null;
    }

    @Override
    public Map<String, String> toFile() {
        String type = "block";
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
}
