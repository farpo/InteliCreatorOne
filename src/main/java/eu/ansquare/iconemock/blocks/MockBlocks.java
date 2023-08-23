package eu.ansquare.iconemock.blocks;
public class MockBlocks {
    private static String createBlock(String name, String block, String itemgroup){
        return block;
    }
    public static final String MOCK_BLOCK = createBlock("mock_block", "new Block(QuiltBlockSettings().create)", "ItemGroups.INGREDIENTS");

}
