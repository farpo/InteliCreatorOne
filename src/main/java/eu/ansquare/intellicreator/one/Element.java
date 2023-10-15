package eu.ansquare.intellicreator.one;

import java.util.Map;

public abstract class Element {
    public String ID;
    public Element(String id){
        this.ID = id;
    }
    public abstract String icon();
    public abstract Map<String, String> toFile();
    public abstract Element fromFile(Map<String,String> inputMap);
    public abstract void write();
    public enum ItemGroup{
        BUILDING_BLOCKS("building_blocks", "Building blocks"),
        COMBAT("combat", "Combat");

        private final String groupKey;
        private final String label;

        ItemGroup(String key, String label){
            this.groupKey = key;
            this.label = label;
        }
        public String key() {
            return groupKey;
        }
        public String toString(){
            return label;
        }
    }
    public String toString(){
        return ID;
    }
}
