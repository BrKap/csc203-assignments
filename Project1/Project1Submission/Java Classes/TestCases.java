import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCases {

    @Test
    public void nodeGetData_1() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);
        LinkedList.Node node1 = digit1.getHead();
        assertEquals(node1.getData(), 1);
    }

    @Test
    public void nodeGetData_2() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);
        assertEquals(digit1.getHead().getData(), 1);
    }

    @Test
    public void nodeGetNext_1() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);
        digit1.insert(2);
        assertEquals(digit1.getHead().getNext(), digit1.getHead().next);
    }

    @Test
    public void nodeGetNext_2() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);
        digit1.insert(2);
        assertEquals(digit1.getHead().getNext(), new LinkedList.Node(1));
    }


    @Test
    public void linkedListGetHead_1() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);
        assertEquals(digit1.getHead(), new LinkedList.Node(1));
    }

    @Test
    public void linkedListInsert_1() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);

        assertEquals(digit1.getString(), "1");
    }

    @Test
    public void linkedListFill_1() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(0);
        LinkedList digit2 = new LinkedList();
        digit2.fill(1);
        
        assertEquals(digit1.getString(), digit2.getString());
    }

    @Test
    public void linkedListFill_2() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(0);
        digit1.insert(0);
        LinkedList digit2 = new LinkedList();
        digit2.fill(2);

        assertEquals(digit1.getString(), digit2.getString());
    }

    @Test
    public void linkedListFill_3() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(0);
        digit1.insert(1);
        LinkedList digit2 = new LinkedList();
        digit2.insert(1);
        digit2.fill(2);

        assertEquals(digit1.getString(), digit2.getString());
    }

    @Test
    public void linkedListGetString_1() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);
        String result = digit1.getString();
        assertEquals(result, "1");
    }

    @Test
    public void linkedListGetString_2() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);
        digit1.insert(2);
        String result = digit1.getString();
        assertEquals(result, "12");
    }

    @Test
    public void linkedListGetString_3() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);
        digit1.insert(2);
        digit1.insert(3);
        String result = digit1.getString();
        assertEquals(result, "123");
    }

    @Test
    public void linkedListGetString_4() {
        LinkedList digit1 = new LinkedList();
        String result = digit1.getString();
        assertEquals(result, "0");
    }

    @Test
    public void linkedList_1() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();

        assertEquals(digit1, digit2);
    }

    @Test
    public void linkedList_2() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);
        LinkedList digit2 = new LinkedList();

        assertNotEquals(digit1, digit2);
    }

    @Test
    public void linkedList_3() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);
        LinkedList digit2 = new LinkedList();
        digit2.insert(1);

        assertEquals(digit1, digit2);
    }

    @Test
    public void linkedList_4() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);
        LinkedList digit2 = new LinkedList();
        digit2.insert(2);

        Assertions.assertNotEquals(digit1, digit2);
    }

    @Test
    public void linkedList_5() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);
        digit1.insert(0);
        LinkedList digit2 = new LinkedList();
        digit2.insert(1);

        Assertions.assertNotEquals(digit1, digit2);
    }

    @Test
    public void linkedListLength_1() {
        LinkedList digit1 = new LinkedList();

        assertEquals(digit1.length(), 0);
    }

    @Test
    public void linkedListLength_2() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);

        assertEquals(digit1.length(), 1);
    }

    @Test
    public void linkedListLength_3() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);
        digit1.insert(2);

        assertEquals(digit1.length(), 2);
    }
    
    @Test
    public void linkedListAdd_1() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();
        digit1.insert(1);
        digit2.insert(2);
        LinkedList result = digit1.add(digit2);
        LinkedList expected = new LinkedList();
        expected.insert(3);

        assertEquals(result, expected);
    }

    @Test
    public void linkedListAdd_2() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();
        digit1.insert(8);
        digit2.insert(7);
        LinkedList result = digit1.add(digit2);
        LinkedList expected = new LinkedList();
        expected.insert(1);
        expected.insert(5);

        assertEquals(result.getString(), expected.getString());
    }

    @Test
    public void linkedListMultiply_1() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();
        digit1.insert(8);
        digit2.insert(7);
        LinkedList result = digit1.multiply(digit2);
        LinkedList expected = new LinkedList();
        expected.insert(5);
        expected.insert(6);

        assertEquals(result.getString(), expected.getString());
    }

    @Test
    public void linkedListMultiply_2() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();
        digit1.insert(1);
        digit1.insert(2);
        digit2.insert(3);
        digit2.insert(4);
        LinkedList result = digit1.multiply(digit2);
        LinkedList expected = new LinkedList();
        expected.insert(4);
        expected.insert(0);
        expected.insert(8);

        assertEquals(result.getString(), expected.getString());
    }

    @Test
    public void linkedListMultiply_3() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();
        digit1.insert(1);
        digit2.insert(1);
        LinkedList result = digit1.multiply(digit2);
        LinkedList expected = new LinkedList();
        expected.insert(1);

        assertEquals(result.getString(), expected.getString());
    }

    @Test
    public void linkedListMultiply_4() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();
        digit1.insert(0);
        digit2.insert(1);
        LinkedList result = digit1.multiply(digit2);
        LinkedList expected = new LinkedList();
        expected.insert(0);

        assertEquals(result.getString(), expected.getString());
    }

    @Test
    public void linkedListMultiply_5() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();
        digit1.insert(1);
        digit2.insert(0);
        LinkedList result = digit1.multiply(digit2);
        LinkedList expected = new LinkedList();
        expected.insert(0);

        assertEquals(result.getString(), expected.getString());
    }

    @Test
    public void linkedListExponent_1() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();
        digit1.insert(2);
        digit2.insert(2);
        LinkedList result = digit1.exponent(digit2);
        LinkedList expected = new LinkedList();
        expected.insert(4);

        assertEquals(result.getString(), expected.getString());
    }

    @Test
    public void linkedListExponent_2() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();
        digit1.insert(2);
        digit2.insert(3);
        LinkedList result = digit1.exponent(digit2);
        LinkedList expected = new LinkedList();
        expected.insert(8);

        assertEquals(result.getString(), expected.getString());
    }

    @Test
    public void linkedListExponent_3() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();
        digit1.insert(2);
        digit2.insert(1);
        digit2.insert(0);
        LinkedList result = digit1.exponent(digit2);
        LinkedList expected = new LinkedList();
        expected.insert(1);
        expected.insert(0);
        expected.insert(2);
        expected.insert(4);

        assertEquals(result.getString(), expected.getString());
    }

    @Test
    public void linkedListExponent_4() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();
        digit1.insert(1);
        digit2.insert(1);
        LinkedList result = digit1.exponent(digit2);
        LinkedList expected = new LinkedList();
        expected.insert(1);

        assertEquals(result.getString(), expected.getString());
    }

    @Test
    public void linkedListExponent_5() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();
        digit1.insert(1);
        digit2.insert(2);
        LinkedList result = digit1.exponent(digit2);
        LinkedList expected = new LinkedList();
        expected.insert(1);

        assertEquals(result.getString(), expected.getString());
    }

    @Test
    public void linkedListExponent_6() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();
        digit1.insert(2);
        LinkedList result = digit1.exponent(digit2);
        LinkedList expected = new LinkedList();
        expected.insert(1);

        assertEquals(result.getString(), expected.getString());
    }

    @Test
    public void linkedListExponent_7() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();
        digit2.insert(1);
        LinkedList result = digit1.exponent(digit2);
        LinkedList expected = new LinkedList();
        expected.insert(0);

        assertEquals(result.getString(), expected.getString());
    }

    @Test
    public void linkedListExponent_8() {
        LinkedList digit1 = new LinkedList();
        LinkedList digit2 = new LinkedList();
        digit2.insert(0);
        LinkedList result = digit1.exponent(digit2);
        LinkedList expected = new LinkedList();
        expected.insert(1);

        assertEquals(result.getString(), expected.getString());
    }

    @Test
    public void linkedListCleanZero_1() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(0);
        digit1.insert(1);
        LinkedList expected = new LinkedList();
        expected.insert(1);
        digit1.cleanZero();

        assertEquals(digit1.getString(), expected.getString());
    }

    @Test
    public void linkedListCleanZero_2() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(0);
        digit1.insert(0);
        digit1.insert(1);
        LinkedList expected = new LinkedList();
        expected.insert(1);
        digit1.cleanZero();

        assertEquals(digit1.getString(), expected.getString());
    }
    @Test
    public void linkedListCleanZero_3() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(0);
        digit1.insert(0);
        digit1.insert(5);
        digit1.insert(0);

        LinkedList expected = new LinkedList();
        expected.insert(5);
        expected.insert(0);
        digit1.cleanZero();

        assertEquals(digit1.getString(), expected.getString());
    }

    @Test
    public void linkedListHalf_1() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(4);
        digit1.half();

        LinkedList expected = new LinkedList();
        expected.insert(2);

        assertEquals(digit1.getString(), expected.getString());
    }

    @Test
    public void linkedListHalf_2() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(5);
        System.out.println("Test Case 'linkedListHalf_2()'");
        digit1.half();

        LinkedList expected = new LinkedList();
        expected.insert(5);

        assertEquals(digit1.getString(), expected.getString());
    }

    @Test
    public void linkedListHalf_3() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);
        digit1.insert(0);
        digit1.half();

        LinkedList expected = new LinkedList();
        expected.insert(5);

        assertEquals(digit1.getString(), expected.getString());
    }

    @Test
    public void linkedListHalf_4() {
        LinkedList digit1 = new LinkedList();
        digit1.insert(1);
        digit1.insert(6);
        digit1.insert(3);
        digit1.insert(6);
        digit1.half();

        LinkedList expected = new LinkedList();
        expected.insert(8);
        expected.insert(1);
        expected.insert(8);

        assertEquals(digit1.getString(), expected.getString());
    }

}
