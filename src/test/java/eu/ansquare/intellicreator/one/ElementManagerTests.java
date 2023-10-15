package eu.ansquare.intellicreator.one;

import eu.ansquare.intellicreator.one.block.BlockElement;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class ElementManagerTests {
    @Test
    public void testLoadAndSave() {
        ElementManager elementManager = new ElementManager(Main.getElementPath());
        elementManager.load();
        BlockElement element = new BlockElement("test");
        element.name("Test elelest");
        elementManager.elements.put(element.ID, element);
        elementManager.save();
    }
    @Test
    public void testWrite() {
        try {
            Files.writeString(Path.of("icone\\written.json"), "[]");
        } catch (IOException e) {
            assert false;
        }
        ElementManager elementManager = new ElementManager(Main.getElementPath());

        elementManager.load();
        elementManager.write();
        elementManager.save();
    }
}
