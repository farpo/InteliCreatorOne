package eu.ansquare.iconemock.items;
public class MockItems {
    private static String createItem(String name, String item, String itemgroup){
        return item;
    }
    public static final String MOCK_ITEM = createItem("mock_item", "new Item(new QuiltItemSettings())", "ItemGroups.INGREDIENTS");

}
