

import org.junit.Test;

import static org.junit.Assert.*;

public class SortTest {
    private int [] expectedAfterSort = new int[] {1,1,2,2,3,5,5,5,6,8};

    @Test
    public void shouldSortByInsert() {
        int[] sort = new int [] {2,5,2,5,3,1,6,8,1,5};
        Sort.Insert( sort );
        assertArrayEquals( expectedAfterSort, sort );
    }

    @Test
    public void shouldSortByBubble() {
        int[] sort = new int [] {2,5,2,5,3,1,6,8,1,5};
        Sort.Bubble( sort );
        assertArrayEquals( expectedAfterSort, sort );
    }

    @Test
    public void shouldSortByCocktail() {
        int[] sort = new int [] {2,5,2,5,3,1,6,8,1,5};
        Sort.Cocktail( sort );
        assertArrayEquals( expectedAfterSort, sort );
    }

    @Test
    public void shouldSortByComb() {
        int[] sort = new int [] {2,5,2,5,3,1,6,8,1,5};
        Sort.Comb( sort );
        assertArrayEquals( expectedAfterSort, sort );
    }

    @Test
    public void shouldSortByHeap() {
        int[] sort = new int [] {2,5,2,5,3,1,6,8,1,5};
        Sort.Heap( sort );
        assertArrayEquals( expectedAfterSort, sort );
    }
}