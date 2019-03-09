import java.util.Comparator;

public class Item implements Comparable<Item> {
    public String name;
    public ItemCondition state;
    public double mass;
    public int quantity;

    public Item(String name, ItemCondition state, double mass, int quantity){
        this.name = name;
        this.state = state;
        this.mass = mass;
        this.quantity = quantity;
    }

    public void print(){
        System.out.println("Item: " + this.name + " state: " + this.state +
                " mass: " + this.mass + " quantity: " + this.quantity);
    }

    public String getName(){
        return this.name;
    }

    @Override
    public int compareTo(Item item) {
        return this.name.compareTo(item.name);
    }
}
