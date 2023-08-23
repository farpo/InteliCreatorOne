package eu.ansquare.iconemock.items;
public class MockItems {
    private static String createItem(String name, String item, String itemgroup){
        return item;
    }
    public static final String MOCK_ITEM = createItem("mock_item", "new Item(new QuiltItemSettings())", "ItemGroups.INGREDIENTS");
    public static final String NAME = createItem("name", "new Item(new QuiltItemSettings())", "ItemGroups.COMBAT"); 
    public static final String OITEM = createItem("oitem", "new Item(new QuiltItemSettings())", "ItemGroups.COMBAT"); 

}
