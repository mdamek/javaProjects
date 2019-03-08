public class Item implements Comparable<Item>{
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

    @Override
    public int compareTo(Item item) {
        if(this.name.equals(item.name)) return 0;
        if(this.name.length() > item.name.length()) return 1;
        return -1;
    }
}
