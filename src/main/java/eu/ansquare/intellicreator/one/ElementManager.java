package eu.ansquare.intellicreator.one;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.ansquare.intellicreator.one.block.BlockElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class ElementManager {
    private ObjectMapper mapper;
    public final String DIRECTORY;
    public Map<String, Element> elements;
    public ElementManager(String directory){
        this.DIRECTORY = directory;
        elements = new HashMap<>();
        mapper = new ObjectMapper();
    }
    public void save(){
        for(String key : elements.keySet()){
            Element element = elements.get(key);
            Map<String, String> outputMap = element.toFile();
            try {
                String outputFromMap = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputMap);
                Files.writeString(Paths.get(DIRECTORY + element.ID + ".json"), outputFromMap);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void load(){
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
                    elements.put(name, element);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
