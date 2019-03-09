import java.util.List;

public class Main {

    public static void main(String args[]){
        Item item1 = new Item("Banan", ItemCondition.NEW, 100, 5);
        Item item2 = new Item("Jablko", ItemCondition.USED, 10, 4);
        Item item3 = new Item("Orzech", ItemCondition.REFURBISHED, 200, 3);
        Item item4 = new Item("Gruszka", ItemCondition.NEW, 300, 2);
        Item item5 = new Item("Ananas", ItemCondition.NEW, 250, 1);

        FulfillmentCenter center = new FulfillmentCenter("Magazyn", 20000);
        center.addProduct(item1);
        center.addProduct(item2);
        center.addProduct(item3);
        center.addProduct(item4);
        center.addProduct(item5);
        List<Item> sortedByAmount = center.sortByAmount();
        center.summary();
        List<Item> sorted = center.sortByName();
        List<Item> searchedPartial = center.searchPartial("a");
        int countedByCondition = center.countByCondition(ItemCondition.NEW);
        Item maxItem = center.max();
    }
}
