package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }


    @Test
    public void testSquarePrimesFirstSix() {
        IntList lst = IntList.of(2, 3, 5, 7, 11, 13);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 9 -> 25 -> 49 -> 121 -> 169", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesZero() {
        IntList lst = IntList.of(0, 0, 0, 0, 0, 0);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("0 -> 0 -> 0 -> 0 -> 0 -> 0", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimesComplex() {
        IntList lst = IntList.of(0, 1, 10, 100, 2, -1);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("0 -> 1 -> 10 -> 100 -> 4 -> -1", lst.toString());
        assertTrue(changed);
    }
}
