import collections.MyCollection;
import collections.MyLinkedList;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

public class TestMyLinkedList {

    /**
     * Test instance of class can be created
     */
    @Test
    public void test_new() {
        // Arrange
        MyLinkedList list;

        // Act
        list = new MyLinkedList();

        // Assert
        assertThat(list, instanceOf(MyLinkedList.class));
    }

    /**
     * Test add() and size()
     */
    @Test
    public void test_add_and_size() {
        // Arrange
        MyLinkedList list = new MyLinkedList();

        // Act
        list.add(new Dummy("1"));
        list.add(new Dummy("2"));

        // Assert
        assertEquals(2, list.size());
    }

    /**
     * Test add(index)
     */
    @Test
    public void test_add_at_index() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        // Act
        list.add(3, new Dummy("three"));
        list.add(4, new Dummy("four"));
        list.add(5, new Dummy("five"));
        list.add(6, new Dummy("six"));

        // Assert
        Dummy d = (Dummy) list.get(3);
        assertEquals("three", d.getValue());
    }

    /**
     * Test add(index) --> shift values to right
     */
    @Test
    public void test_add_index_shift() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        //Act
        list.add(4, new Dummy("Fourth index"));

        //Assert "5" at index 5
        assertEquals("5", ((Dummy) list.get(5)).getValue());
    }

    /**
     * Test add(index) --> IndexOutOfBoundsException high
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void test_add_at_index_exception_high() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        // Act
        list.add(10, new Dummy("Out of bounds"));
        fail("IndexOutOfBoundsException expected");

        // Assert --> (expected = IndexOutOfBoundsException.class)
    }

    /**
     * Test add(index) --> IndexOutOfBoundsException low
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void test_add_at_index_exception_low() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        // Act
        list.add(-1, new Dummy("Out of bounds"));
        fail("IndexOutOfBoundsException expected");

        // Assert --> (expected = IndexOutOfBoundsException.class)
    }

    /* TEST DE MESSAGE D'ERREUR: */
    //        try {
    //            // Act
    //            list.set(11, new Dummy("Out of bounds"));
    //            fail("IndexOutOfBoundsException expected");
    //        } catch (IndexOutOfBoundsException e) {
    //            // Assert
    //            assertEquals("Index 11 out of bounds for length 11", e.getMessage());
    //        }

    /**
     * Test get(index)
     */
    @Test
    public void test_get() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        //Act
        Dummy d = (Dummy) list.get(3);

        //Assert "4" at index 3
        assertEquals("4", d.getValue());
    }

    /**
     * Test get(index) --> IndexOutOfBoundsException high
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void test_get_exception_high() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        // Act
        Dummy d = (Dummy) list.get(18);
        fail("IndexOutOfBoundsException expected");

        // Assert --> (expected = IndexOutOfBoundsException.class)
    }

    /**
     * Test get(index) --> IndexOutOfBoundsException low
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void test_get_exception_low() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        // Act
        Dummy d = (Dummy) list.get(-1);
        fail("IndexOutOfBoundsException expected");

        // Assert --> (expected = IndexOutOfBoundsException.class)
    }

    /**
     * Test set()
     */
    @Test
    public void test_set() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        // Act
        list.set(5, new Dummy("Updated 5 index"));

        // Assert
        assertEquals("Updated 5 index", ((Dummy) list.get(5)).getValue());
        assertEquals("7", ((Dummy) list.get(6)).getValue());
    }

    /**
     * Test set() --> IndexOutOfBoundsException high
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void test_set_exception_high() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        // Act
        list.set(10, new Dummy("Out of bounds"));
        fail("IndexOutOfBoundsException expected");

        // Assert --> (expected = IndexOutOfBoundsException.class)
    }

    /**
     * Test set() --> IndexOutOfBoundsException low
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void test_set_exception_low() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        // Act
        list.set(-1, new Dummy("Out of bounds"));
        fail("IndexOutOfBoundsException expected");

        // Assert --> (expected = IndexOutOfBoundsException.class)
    }

    /**
     * Test remove()
     */
    @Test
    public void test_remove() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        //Act
        list.remove(4);

        //Assert "7" at index 5
        assertEquals("7", ((Dummy) list.get(5)).getValue());
    }

    /**
     * Test clear()
     */
    @Test
    public void test_clear() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        //Act
        list.clear();

        //Assert has been cleared
        assertEquals(0, list.size());
    }

    /**
     * Test isEmpty() --> True
     */
    @Test
    public void test_isEmpty_true() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        //Act
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);

        //Assert true isEmpty
        assertTrue(list.isEmpty());
    }

    /**
     * Test isEmpty() --> False
     */
    @Test
    public void test_isEmpty_false() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        //Act
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);

        //Assert False isEmpty
        assertFalse(list.isEmpty());
    }

    /**
     * Test contains() --> True
     */
    @Test
    public void test_contains_true() {
        // Arrange & Act
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        //Assert list contains Dummy("7")
        assertTrue(list.contains(new Dummy("7")));
    }

    /**
     * Test contains() --> False
     */
    @Test
    public void test_contains_false() {
        // Arrange & Act
        MyLinkedList list = new MyLinkedList();
        initArrayWithSevenValues(list);

        //Assert list does not contains Dummy("0")
        assertFalse(list.contains(new Dummy("0")));
    }

    /**
     * Test indexOf()
     */
    @Test
    public void test_indexOf() {
        // Arrange
        MyLinkedList list = new MyLinkedList();

        //Act
        initArrayWithSevenValues(list);
        list.add(new Dummy("7"));
        list.add(new Dummy("7"));

        //Assert "7" at index 6
        assertEquals(list.indexOf(new Dummy("7")), 6);
    }

    /**
     * Test indexOf() not found
     */
    @Test
    public void test_indexOf_notFound() {
        // Arrange
        MyLinkedList list = new MyLinkedList();

        //Act
        initArrayWithSevenValues(list);

        //Assert 19 not found (-1)
        assertEquals(list.indexOf(new Dummy("19")), -1);
    }

    /**
     * initialises a given list with 7 values
     *
     * @param list the list to be filled in
     */
    private void initArrayWithSevenValues(MyCollection list) {

        list.add(new Dummy("1"));
        list.add(new Dummy("2"));
        list.add(new Dummy("3"));
        list.add(new Dummy("4"));
        list.add(new Dummy("5"));
        list.add(new Dummy("6"));
        list.add(new Dummy("7"));
    }

}
