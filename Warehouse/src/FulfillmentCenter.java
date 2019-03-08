import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FulfillmentCenter {
    public String name;
    public List<Item> items;
    public double maxMass;

    FulfillmentCenter(String name, double maxMass){
        this.name = name;
        this.maxMass = maxMass;
        this.items = new ArrayList<>();

    }
    void addProduct(Item item){
        if(getActualMass() + item.mass <= this.maxMass)
            if(isFulfillmentContains(item)) {
                IncreaseItemAmount(item);
            }
            else
                items.add(item);
        else {
            System.err.println("In " + this.name + "-FulfillmentCenter exceeded max Mass: " + this.maxMass);
        }
    }

    public void getProduct(Item item){
        for (Item actualItem: items){
            if(actualItem.equals(item)) {
                actualItem.quantity --;
                if(actualItem.quantity == 0) items.remove(item);
                return;
            }
        }
    }

    public void removeProduct(Item item){
        for (Item actualItem: items){
            if(actualItem.equals(item))
                items.remove(item);
        }
    }

    /*public Item search(String name){

    }*/

    void summary(){
        for (Item actualItem: items){
            actualItem.print();
        }
    }

    public double getActualMass(){
        return items.stream().mapToDouble(e -> e.mass).sum();
    }

    private boolean isFulfillmentContains(Item item){
        for (Item actualItem: items){
            if(actualItem.name.equals(item.name)) return true;
        }
        return false;
    }

    private void IncreaseItemAmount(Item item){
        for (Item actualItem: items){
            if(actualItem.name.equals(item.name)) actualItem.quantity++;
        }
    }
}
