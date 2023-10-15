package eu.ansquare.intellicreator.one.item;

import eu.ansquare.intellicreator.one.Element;

import java.util.Map;

public class ItemElement extends Element {
    public ItemElement(String id) {
        super(id);
    }

    @Override
    public String icon() {
        return null;
    }

    @Override
    public Map<String, String> toFile() {
        return null;
    }

    @Override
    public Element fromFile(Map<String, String> inputMap) {
        return this;

    }

    @Override
    public void write() {

    }

}
