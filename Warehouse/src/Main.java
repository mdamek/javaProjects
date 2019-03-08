public class Main {

    public static void main(String args[]){
        Item item1 = new Item("Banan", ItemCondition.NEW, 600, 1);
        Item item2 = new Item("Jablko", ItemCondition.USED, 10, 1);
        Item item3 = new Item("Orzech", ItemCondition.REFURBISHED, 200, 1);
        Item item4 = new Item("Gruszka", ItemCondition.NEW, 300, 1);
        Item item5 = new Item("Ananas", ItemCondition.NEW, 250, 1);

        FulfillmentCenter center = new FulfillmentCenter("Magazyn", 500);
        center.addProduct(item1);
        center.summary();
    }
}
