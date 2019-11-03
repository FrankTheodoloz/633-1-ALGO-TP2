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
        Dummy d = (Dummy) list.get(1);
        assertEquals("2", d.getValue());
    }

    /**
     * Test add(index)
     */
    @Test
    public void test_add_at_index() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithValues(list);

        // Act
        list.add(3, new Dummy("test"));

        // Assert
        Dummy d = (Dummy) list.get(3);
        assertEquals("test", d.getValue());
    }

    /**
     * Test add(index) --> shift values to right
     */
    @Test
    public void test_add_index_shift() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithValues(list);

        //Act
        list.add(4, new Dummy("test"));

        //Assert
        Dummy dBefore = (Dummy) list.get(3);
        Dummy dAfter = (Dummy) list.get(5);
        assertEquals("E index 3", dBefore.getValue());
        assertEquals("C index 4", dAfter.getValue());
    }

    /**
     * Test add(index) --> IndexOutOfBoundsException high
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void test_add_at_index_exception_high() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithValues(list);

        // Act
        list.add(10, new Dummy("test"));
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
        initArrayWithValues(list);

        // Act
        list.add(-1, new Dummy("test"));
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
        initArrayWithValues(list);

        //Act
        Dummy d = (Dummy) list.get(3);

        //Assert "4" at index 3
        assertEquals("E index 3", d.getValue());
    }

    /**
     * Test get(index) --> IndexOutOfBoundsException high
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void test_get_exception_high() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithValues(list);

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
        initArrayWithValues(list);

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
        initArrayWithValues(list);

        // Act
        list.set(5, new Dummy("Updated 5 index"));

        // Assert
        assertEquals("Updated 5 index", ((Dummy) list.get(5)).getValue());
    }

    /**
     * Test set() --> IndexOutOfBoundsException high
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void test_set_exception_high() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithValues(list);

        // Act
        list.set(10, new Dummy("test"));
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
        initArrayWithValues(list);

        // Act
        list.set(-1, new Dummy("test"));
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
        initArrayWithValues(list);

        //Act
        list.remove(4);

        //Assert
        Dummy d = (Dummy) list.get(4);
        assertEquals("A index 5", d.getValue());
    }

    /**
     * Test clear()
     */
    @Test
    public void test_clear() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithValues(list);

        //Act
        list.clear();

        //Assert has been cleared
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    /**
     * Test size()
     */
    @Test
    public void test_size() {
        // Arrange
        MyLinkedList list = new MyLinkedList();

        // Act
        list.add(new Dummy("1"));
        list.add(new Dummy("2"));

        // Assert
        assertEquals(2, list.size());
    }

    /**
     * Test isEmpty() --> True
     */
    @Test
    public void test_isEmpty_true() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        list.add(new Dummy("1"));

        //Act
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
        list.add(new Dummy("1"));
        list.add(new Dummy("2"));


        //Act
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
        initArrayWithValues(list);

        // Act
        Dummy d = new Dummy("A index 5");
        boolean isPresent = list.contains(d);

        //Assert
        assertTrue(isPresent);
    }

    /**
     * Test contains() --> False
     */
    @Test
    public void test_contains_false() {
        // Arrange & Act
        MyLinkedList list = new MyLinkedList();
        initArrayWithValues(list);

        // Act
        Dummy d = new Dummy("index 8");
        boolean isPresent = list.contains(d);

        //Assert
        assertFalse(isPresent);
    }

    /**
     * Test indexOf()
     */
    @Test
    public void test_indexOf() {
        // Arrange
        MyLinkedList list = new MyLinkedList();

        //Act
        initArrayWithValues(list);
        list.add(new Dummy("New index 3"));

        //Assert
        Dummy d = new Dummy("New index 3");
        assertEquals(list.indexOf(new Dummy("E index 3")), 3);
    }

    /**
     * Test indexOf() not found
     */
    @Test
    public void test_indexOf_notFound() {
        // Arrange
        MyLinkedList list = new MyLinkedList();

        //Act
        initArrayWithValues(list);

        //Assert
        Dummy d = new Dummy("index 8");
        assertEquals(list.indexOf(d), -1);
    }

    /**
     * Test compareTo smaller
     */
    @Test
    public void text_compareTo_smaller() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithValues(list);

        //Act
        int comparison = list.get(1).compareTo(list.get(2)); // plus petit (B < G)

        //Assert
        assertTrue(comparison < 0);
    }

    /**
     * Test compareTo bigger
     */
    @Test
    public void text_compareTo_bigger() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithValues(list);

        //Act
        int comparison = list.get(2).compareTo(list.get(1)); // plus grand (F > B)

        //Assert
        assertTrue(comparison > 0);
    }

    @Test
    public void test_trifusion() {
        // Arrange
        MyLinkedList list = new MyLinkedList();
        initArrayWithValues(list);

        //Act
        list.fusionSort();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // Assert
        assertEquals(list.indexOf(new Dummy("A index 5")), 0);
        assertEquals(list.indexOf(new Dummy("B index 1")), 1);
        assertEquals(list.indexOf(new Dummy("C index 4")), 2);
        assertEquals(list.indexOf(new Dummy("D index 6")), 3);
        assertEquals(list.indexOf(new Dummy("E index 3")), 4);
        assertEquals(list.indexOf(new Dummy("F index 0")), 5);
        assertEquals(list.indexOf(new Dummy("G index 2")), 6);
        assertEquals(list.indexOf(new Dummy("H index 7")), 7);
    }

    /**
     * initialises a given list with values
     *
     * @param list the list to be filled in
     */
    private void initArrayWithValues(MyCollection list) {

        list.add(new Dummy("F index 0"));
        list.add(new Dummy("B index 1"));
        list.add(new Dummy("G index 2"));
        list.add(new Dummy("E index 3"));
        list.add(new Dummy("C index 4"));
        list.add(new Dummy("A index 5"));
        list.add(new Dummy("D index 6"));
        list.add(new Dummy("H index 7"));
    }

}
