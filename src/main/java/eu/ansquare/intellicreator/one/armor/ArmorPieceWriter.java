package eu.ansquare.intellicreator.one.armor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.ansquare.intellicreator.one.item.ItemElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ArmorPieceWriter {
    public ObjectMapper mapper = new ObjectMapper();
    public ItemElement fromString(String string){
        if(string.equals("empty")){
            return null;
        }
        Map<String, String> map;
        try {
            map = mapper.readValue(string.getBytes(), HashMap.class);
            ItemElement element = new ItemElement(map.get("id"));
            element.fromFile(map);
            return element;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getString(ItemElement element){
        if(element == null){
            return "empty";
        }
        Map<String, String> map = element.toFile();
        map.put("id", element.ID);
        try {
            String outputFromMap = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
            return outputFromMap;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "empty";
    }
}
