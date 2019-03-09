import java.util.Comparator;

public class QuantityComparator implements Comparator<Item> {
    @Override
    public int compare(Item item1, Item item2){
        return Integer.compare(item1.quantity, item2.quantity);
    }
}
