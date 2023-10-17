package eu.ansquare.intellicreator.one;

import eu.ansquare.intellicreator.one.block.BlockElement;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class ElementManagerTests {
    @Test
    public void testWrite() {
        try {
            Files.writeString(Path.of("icone\\written.json"), "[]");
        } catch (IOException e) {
            assert false;
        }
        ElementManager elementManager = new ElementManager(Main.getElementPath());
        Main.elementManager = elementManager;
        elementManager.load();
        elementManager.write();
        elementManager.save();
    }
    @Test
    public void testLoadArmor(){
        ElementManager elementManager = new ElementManager(Main.getElementPath());
        Main.elementManager = elementManager;
        elementManager.load();
        System.out.println("loaded");
    }
}
