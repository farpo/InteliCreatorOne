package eu.ansquare.intellicreator.one;

import eu.ansquare.intellicreator.one.block.BlockElement;
import org.junit.jupiter.api.Test;

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
        ElementManager elementManager = new ElementManager(Main.getElementPath());
        elementManager.load();
        elementManager.write();
    }
}
