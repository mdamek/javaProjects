import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FulfillmentCenter {
    String name;
    List<Item> items;
    double maxMass;

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

    public Item search(String key){

        //return items.stream().filter(i -> i.name.equals(key)).findFirst().orElse(null);
        int index = Collections.binarySearch(items,
                                             new Item(key, ItemCondition.NEW, 1,1),
                                             Comparator.comparing(Item::getName));
        return items.get(index);
    }


List<Item> searchPartial(String phrase){
        return items.stream().filter(a -> a.name.contains(phrase)).collect(Collectors.toList());
    }

    int countByCondition(ItemCondition itemCondition){
        return items.stream().filter(a -> a.state == itemCondition).collect(Collectors.toList()).size();
    }

    List<Item> sortByName(){
        return items.stream().sorted().collect(Collectors.toList());
    }

    List<Item> sortByAmount(){
        List<Item> sortedItems = new ArrayList<>(items);
        sortedItems.sort(new QuantityComparator());
        return sortedItems;
    }

    Item max(){
        return Collections.max(items, new QuantityComparator());
    }

    void summary(){
        items.forEach(Item::print);
    }

    double getActualMass(){
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
            if(actualItem.name.equals(item.name)) {
                actualItem.quantity++;
                return;
            }
        }
    }
}
