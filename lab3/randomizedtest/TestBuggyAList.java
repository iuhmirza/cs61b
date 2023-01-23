package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {

    @Test
    public void testThreeAndRemove () {
        AListNoResizing<Integer> noResizing = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();
        for (int i = 4; i < 7; i++) {
            noResizing.addLast(i);
            buggy.addLast(i);
        }
        for (int i = 6; i > 3; i--) {
            assertEquals(i,(int) noResizing.removeLast());
            assertEquals(i,(int) buggy.removeLast());
        }
    }

    @Test
    public void randomisedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> M = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                M.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeM = M.size();
                assertEquals(sizeL, sizeM);
            } else if (operationNumber == 2) {
                if (L.size() > 0) {
                    int lastL = L.getLast();
                    int lastM = M.getLast();
                    assertEquals(lastL, lastM);
                }
            } else {
                if (L.size() > 0) {
                    int sizeBeforeL = L.size();
                    int lastL = L.removeLast();
                    int sizeAfterL = L.size();
                    int sizeBeforeM = M.size();
                    int lastM = M.removeLast();
                    int sizeAfterM = M.size();
                    assertEquals(sizeBeforeL, sizeBeforeM);
                    assertEquals(lastL, lastM);
                    assertEquals(sizeAfterL, sizeAfterM);
                }
            }

        }
    }
}
