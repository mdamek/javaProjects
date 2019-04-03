
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MyExceptionsTest {

    @Test(expected = TooLongListException.class)
    public void shouldThrowTooLongListException() throws TooBigNumberException, TooLongListException {
        List<Integer> numbers = new ArrayList<>();
        for ( int i = 0; i < 1000000; i++ ){
            numbers.add( i );
        }
        Algorithms.solution( numbers );
    }

    @Test(expected = TooBigNumberException.class)
    public void shouldThrowTooBigNumberException() throws TooBigNumberException, TooLongListException {
        List<Integer> numbers = new ArrayList<>();
        numbers.add( 10000000 );
        Algorithms.solution( numbers );
    }
}
