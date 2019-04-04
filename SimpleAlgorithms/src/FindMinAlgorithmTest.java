import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindMinAlgorithmTest {

    @Test
    public void shouldProperlyCalculateValuesBetween() throws TooBigNumberException, TooLongListException {
        List<Integer> numbers = new ArrayList<>();
        numbers.add( 1 );
        numbers.add( 2 );
        numbers.add( 3 );
        numbers.add( 4 );
        numbers.add( 5 );
        numbers.add( 6 );
        numbers.add( 7 );
        numbers.add( 8 );
        numbers.add( 10 );
        int answer = Algorithms.solution( numbers);
        int expectedAnswer = 9;
        assertEquals(expectedAnswer, answer);
    }

    @Test
    public void shouldProperlyCalculateValuesOnStart() throws TooBigNumberException, TooLongListException {
        List<Integer> numbers = new ArrayList<>();
        numbers.add( 2 );
        numbers.add( 3 );
        numbers.add( 4 );
        numbers.add( 5 );
        numbers.add( 6 );
        numbers.add( 7 );
        numbers.add( 8 );
        numbers.add( 9 );
        numbers.add( 10 );
        int answer = Algorithms.solution( numbers);
        int expectedAnswer = 1;
        assertEquals(expectedAnswer, answer);
    }

    @Test
    public void shouldProperlyCalculateValuesMax() throws TooBigNumberException, TooLongListException {
        List<Integer> numbers = new ArrayList<>();
        numbers.add( 1 );
        numbers.add( 2 );
        numbers.add( 3 );
        numbers.add( 4 );
        numbers.add( 5 );
        numbers.add( 6 );
        numbers.add( 7 );
        numbers.add( 8 );
        numbers.add( 9 );
        int answer = Algorithms.solution( numbers);
        int expectedAnswer = 10;
        assertEquals(expectedAnswer, answer);
    }
}
