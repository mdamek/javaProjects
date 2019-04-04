import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class FindSumsTest {
    @Test
    public void shouldFindProperlySum() {
        float[] arr = {2, 7, 11, 15};
        float target = 9;
        int [] expectedIndexes = new int[]{0,1};
        int[] indexes = Algorithms.solution( arr, target );
        assertArrayEquals( expectedIndexes, indexes );
    }

    @Test
    public void shouldFindProperlySumOnBoundaryValues () {
        float[] arr = {2, 7, 11, 15};
        float target = 17;
        int [] expectedIndexes = new int[]{0,3};
        int[] indexes = Algorithms.solution( arr, target );
        assertArrayEquals( expectedIndexes, indexes );
    }
}
