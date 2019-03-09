import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FulfillmentCenterContainer {
    public Map<String, FulfillmentCenter> fulfillmentCenters;

    public FulfillmentCenterContainer(){
        this.fulfillmentCenters = new HashMap<>();
    }

    public void addCenter(String name, double maxMass){
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(name, maxMass);
        this.fulfillmentCenters.put(name, fulfillmentCenter);
    }

    public void removeCenter(String name){
        this.fulfillmentCenters.remove(name);
    }

    public List<FulfillmentCenter> findEmpty(){
        return fulfillmentCenters.values().stream().filter(a -> a.items.isEmpty()).collect(Collectors.toList());
    }

    public void summary(){
        fulfillmentCenters.values().forEach(a -> System.out.println("FulfillmentCenter-name: " + a.name +
                " Percentage fill: " + a.getActualMass() / a.maxMass * 100 + "%"));
    }
}
