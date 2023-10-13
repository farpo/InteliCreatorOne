package eu.ansquare.intellicreator.one;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.ansquare.intellicreator.one.block.BlockElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class ElementManager {
    public final String DIRECTORY;
    public Map<String, Element> elements;
    public ElementManager(String directory){
        this.DIRECTORY = directory;
        elements = new HashMap<>();
    }
    public void load(){
        ObjectMapper mapper = new ObjectMapper();
        File directory = Path.of(DIRECTORY).toAbsolutePath().toFile();
        try {
            if(directory.isDirectory()) {
                for (File file : directory.listFiles()) {
                    HashMap<String, String> input = mapper.readValue(Files.readAllBytes(file.toPath().toAbsolutePath()), HashMap.class);
                    String name = file.getName().replace(".json", "");
                    String type = input.get("type");
                    Element element;
                    switch (type){
                        case "block":
                            element = new BlockElement(name);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized type");
                    }
                    element.fromFile(input);
                    elements.put(file.getName().replace(".json", ""), element);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
