package eu.ansquare.intellicreator.one.util;

import java.util.Map;
import java.util.Objects;

public class CollectionUtils {
    public static boolean containsKeys(Map map, Object... objects){
        for (Object object: objects) {
            if(!map.containsKey(object)){
                return false;
            }
        }
        return true;
    }
    public static boolean isNotNull(String... strings){
        for(String string:strings){
            if(string == null){
                return false;
            }
        }
        return true;
    }
    public static void putAll(Map map, Object[] keys, String... values){
        if(keys.length != values.length){
            return;
        }
        for(int i = 0; i < keys.length; i++){
            Object key = keys[i];
            String value = values[i];
            if(value != null){
                map.put(key, value);
            } else {
                map.put(key, "");
            }
        }
    }
}
