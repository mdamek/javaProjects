package images.demo;

import java.util.HashMap;
import java.util.Map;

public class Histogram {

    private Map<Integer, Integer> Blue = new HashMap<>(  );
    private Map<Integer, Integer> Red = new HashMap<>(  );
    private Map<Integer, Integer> Green = new HashMap<>(  );

    Histogram(){
        for ( int i = 0; i < 256; i++ ){
            Blue.put( i, 0 );
            Red.put( i, 0 );
            Green.put( i, 0 );
        }
    }

    public void AddToGreen(int value){
        int actualValue = Green.remove( value );
        Green.put( value, ++actualValue );
    }

    public void AddToBlue(int value){
        int actualValue = Blue.remove( value );
        Blue.put( value, ++actualValue );
    }

    public void AddToRed(int value){
        int actualValue = Red.remove( value );
        Red.put( value, ++actualValue );
    }
}
