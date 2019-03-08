import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FulfillmentCenterContainer {
    private Map<String, FulfillmentCenter> fulfillmentCenters;

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
        List<FulfillmentCenter> emptyFulfillmentCenters = new ArrayList<>();
        for(Map.Entry<String, FulfillmentCenter> element : fulfillmentCenters.entrySet()) {
            if(element.getValue().items.isEmpty())
                emptyFulfillmentCenters.add(element.getValue());
        }
        return emptyFulfillmentCenters;
    }

    public void summary(){
        for(Map.Entry<String, FulfillmentCenter> element : fulfillmentCenters.entrySet()) {
            System.out.println("FulfillmentCenter name: " + element.getValue().name +
                    "Percentage fill: " + element.getValue().getActualMass() / element.getValue().maxMass);
        }
    }
}
