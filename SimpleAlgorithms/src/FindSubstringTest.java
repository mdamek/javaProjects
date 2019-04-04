import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FindSubstringTest {
    @Test
    public void shouldFindSubstringFirstBigger() {
        String firstString = "abbbbbbbbba";
        String substring = "aa";
        int expectedRepeats = 2;
        int repeats = Algorithms.substring( firstString, substring );
        assertEquals( expectedRepeats, repeats );
    }

    @Test
    public void shouldFindSubstringNormal() {
        String firstString = "abcab";
        String substring = "abc";
        int expectedRepeats = 1;
        int repeats = Algorithms.substring( firstString, substring );
        assertEquals( expectedRepeats, repeats );
    }

    @Test
    public void shouldNotFindSubstring() {
        String firstString = "abbbba";
        String substring = "ac";
        int expectedRepeats = -1;
        int repeats = Algorithms.substring( firstString, substring );
        assertEquals( expectedRepeats, repeats );
    }
}