/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @Disabled("TODO revise test logic")
    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        setB.add(11);
        assertTrue(setB.contains(11), "add: added element not found in set.");
        assertEquals(7, setB.size(), "add: elements count not as expected.");
    }

    @Test
    public void testDuplicate() {
        assertTrue(setB.contains(30));
        assertThrows(IllegalArgumentException.class, () -> setB.add(30), "add: added element was added but already is in set");
    }

    @Test
    public void testNonPositive() {
        assertThrows(IllegalArgumentException.class, () -> setA.add(0), "add: zero added but it is not positive");
        assertThrows(IllegalArgumentException.class, () -> setA.add(-21), "add: negative value added but it is not positive");
    }

    @Disabled("TODO revise to test the construction from invalid arrays")
    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }

    @Test
    public void testDuplicateFromArray() {
        assertThrows(IllegalArgumentException.class, () -> BoundedSetOfNaturals.fromArray(new int[]{15,33,40,33}));
    }

    @Test
    public void testNonPositiveFromArray() {
        assertThrows(IllegalArgumentException.class, () -> BoundedSetOfNaturals.fromArray(new int[]{15,33,40,-7}));
        assertThrows(IllegalArgumentException.class, () -> BoundedSetOfNaturals.fromArray(new int[]{15,33,40,0}));
    }

    @Test
    public void testIntersection() {
        assertTrue(setB.intersects(BoundedSetOfNaturals.fromArray(new int[]{15,33,40,41})));
        assertFalse(setB.intersects(BoundedSetOfNaturals.fromArray(new int[]{15,33,41})));
    }


    @Test
    public void testNoIntersection() {
        assertFalse(setA.intersects(setB), "no intersection");

    }

}
