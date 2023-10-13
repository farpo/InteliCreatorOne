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
    public enum ItemGroup{
        BUILDING_BLOCKS("building_blocks"),
        COMBAT("combat");

        private final String groupKey;
        ItemGroup(String key){
            this.groupKey = key;
        }
        public String key() {
            return groupKey;
        }
    }
}
