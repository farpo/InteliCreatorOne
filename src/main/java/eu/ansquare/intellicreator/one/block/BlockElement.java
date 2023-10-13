package eu.ansquare.intellicreator.one.block;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.util.CollectionUtils;

import java.util.Map;

public class BlockElement extends Element {
    public String name;
    public String texture;
    public String model;
    public Element.ItemGroup itemGroup;

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
        return null;
    }

    @Override
    public Element fromFile(Map<String, String> inputMap) {
        if(CollectionUtils.containsKeys(inputMap, "name", "texture", "model")){
            name = inputMap.get("name");
            texture = inputMap.get("texture");
            model = inputMap.get("model");
            return this;
        } else {
            throw new RuntimeException("Element misformed");
        }
    }
}
