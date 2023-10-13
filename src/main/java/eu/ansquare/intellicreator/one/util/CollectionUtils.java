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
}
