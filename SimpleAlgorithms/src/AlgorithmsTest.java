import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AlgorithmsTest {

    @Test
    public void shouldProperlyCalculateValues() throws ElementNotExistException, TooBigNumberException, TooLongListException {
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
    public void shouldFindSubstring() {
        String firstString = "abbbbbbbbba";
        String substring = "aa";
        int expectedRepeats = 2;
        int repeats = Algorithms.substring( firstString, substring );
        assertEquals( expectedRepeats, repeats );
    }

    @Test
    public void shouldFindProperlySum() {
        float[] arr = {2, 7, 11, 15};
        float target = 9;
        int [] expectedIndexes = new int[]{0,1};
        int[] indexes = Algorithms.solution( arr, target );
        assertArrayEquals( expectedIndexes, indexes );

    }
}