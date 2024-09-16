import java.util.ArrayList;
import java.util.List;

public class LinkedList {
    private Node head;

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        public int getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        @Override
        public String toString() {
            return "Node(" + this.data + ", " + this.next + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this==obj) return true;
            if (this != null && obj == null) return false;
            if (this.getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return (this.data == node.getData()) &&
                    (this.next == node.getNext());
        }
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getHead() {
        return head;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        LinkedList linkedlist = (LinkedList) obj;
        if (this.head == null && linkedlist.getHead() == null) return true;
        return (this.head.equals(linkedlist.getHead()));
    }

/*
Insert a single digit into the LinkedList
The digit is placed at the front of the list
The first digit in the LinkedList represents the 1's place
The second digit in the LinkedList represents the 10's place
and so on until there is null representing the end of the number
 */
    public void insert(int data) {
        Node new_node = new Node(data);
        // Testing if the number is a positive single digit number
        // Otherwise do nothing and print in console that it must be single digit
        if (data < 10 && data >= 0) {
            if (this.head == null) {
                this.head = new_node;
            }
            else {
                new_node.next = this.head;
                this.head = new_node;
            }
        } else {
            System.out.println("Integer must be a single digit number");
        }

    }
/*
Fill in 0's into the list in order to create a LinkedList of length that is specified.
The 0's will go at the end of the list meaning if I had a number 12,
and I wanted to increase length to 4, the resulting number will be 0012
 */
    public void fill(int value) {
        int length = this.length();
        while (length < value) {
            Node new_node = new Node(0);

            if (head == null) {
                head = new_node;
            }
            else {
                Node last = head;
                while (last.next != null) {
                    last = last.next;
                }
                last.next = new_node;
            }
            length++;
        }
    }

/*
Calculates the length of the LinkedList by traversing and increasing
counter by 1 until it reaches null
 */
    public int length() {
        int length = 0;
        Node temp = this.head;
        while (!(temp == null)) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    @Override
    public String toString() {
        return "LinkedList(" + this.head + ")";
    }

    /*
    Turns the LinkedList into a string to output to console
    or write to file. It automatically removes 0's in front if
    the zero's have not been removed yet.
    It makes sure that if the LinkedList is empty or has multiple zero's
    like "0000" it will only return a single "0"
     */
    public String getString() {
        String num = "";
        Node temp = this.head;
        while (temp != null) {
            num = temp.data + num;
            temp = temp.next;
        }
        num = num.replaceAll("^0+", "");
        if (num.isEmpty() || num.equals("0")) {
            return "0";
        }
        return num;
    }

    /*
    Add 2 Linked Lists together.
    Doesn't matter which linkedlist is the object or other
    the sum will still result in the same and return a single
    Linked list.
    This adds digit by digit, making sure to carry over excess numbers above 9
    into the next slot in the Linked List
     */

    public LinkedList add(LinkedList other) {
        String sum = "";
        Node temp1 = this.head;
        Node temp2 = other.head;

        int length = this.length();
        int otherLength = other.length();
        if (length != otherLength) {
            if (length > otherLength) {
                other.fill(length);
            } else {
                this.fill(otherLength);
            }
        }
        int nextPlace = 0;
        while (temp1 != null) {
            int tempsum = temp1.data + temp2.data;
            if (nextPlace != 0) {
                tempsum = tempsum + nextPlace;
                nextPlace = 0;
            }
            while (tempsum > 9) {
                tempsum = tempsum - 10;
                nextPlace++;
            }
            sum = tempsum + sum;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        if (nextPlace != 0) {
            sum = nextPlace + sum;
        }

        LinkedList result = new LinkedList();
        char[] sumArray = sum.toCharArray();
        for (char c : sumArray) {
            result.insert(Integer.parseInt(String.valueOf(c)));
        }
        result.cleanZero();
        return result;
    }

    /*
    Multiplies 2 Linked List together
    Doesn't matter which linkedlist is the object or other
    the result will still result in the same and return a single
    Linked list.
    This multiplies digit by digit, making sure to carry over excess numbers above 9
    into the next slot in the Linked List.
    I want to work with the shortest LinkedList (Length) to act as my base loop
    to minimize unecessary calculations.
    The first digit will multiply into all of other's digits and store
    that value as a linked list
    Then I will move onto the second digit and repeating the same, but adding a zero in front
    of order to make it x10 of the previous.
    Then call add function to add every single one of the stored Linked List to get the final result
     */

    public LinkedList multiply(LinkedList other) {
        Node temp1 = this.head;
        Node temp3 = other.head;

        int length = this.length();
        int otherLength = other.length();
        if (length != otherLength) {
            if (length > otherLength) {
                temp1 = other.head;
                temp3 = this.head;
            }
        }

        List<LinkedList> sums = new ArrayList<>();

        while (temp1 != null) {
            int digit1 = temp1.data;
            int nextPlace = 0;
            String tempValue = "";
            Node temp2 = temp3;

            while (temp2 != null) {
                int digit2 = temp2.data;
                int product = digit1 * digit2 + nextPlace;
                nextPlace = 0;

                while (product > 9) {
                    product = product - 10;
                    nextPlace++;
                }

                tempValue = product + tempValue;
                temp2 = temp2.next;
            }
            if (nextPlace != 0) {
                tempValue = nextPlace + tempValue;
            }

            LinkedList placeHolder = new LinkedList();

            char[] tempArray = tempValue.toCharArray();
            for (char c : tempArray) {
                placeHolder.insert(Integer.parseInt(String.valueOf(c)));
            }
            sums.add(placeHolder);
            temp1 = temp1.next;
        }
        for (int i = 1; i < sums.size(); i++) {
            int counter = i;
            LinkedList temp = sums.get(i);
            while (counter > 0) {
                temp.insert(0);
                counter--;
            }
            sums.set(i, temp);
        }
        LinkedList result = new LinkedList();
        result.insert(0);
        for (LinkedList sum : sums) {
            result = result.add(sum);
        }
        result.cleanZero();
        return result;
    }

    /*
    This function will divide the LinkedList by 2 (basically getting half of the linkedList)
    This will only work if the linkedList is an even number otherwise it would result in decimals
    and that is outside of this projects scope.
    Do nothing if it is odd
     */
    public void half() {
        // Only divide by half if it is an even number
        // Else do nothing
        if (this.head.data % 2 == 0) {
            Node start = this.head;
            //divide the linked list by 2
            String halfNum = "";
            while (start.next != null) {
                int currentHalf = start.data / 2;
                if (start.next.data % 2 == 1) {
                    currentHalf = currentHalf + 5;
                }
                halfNum = currentHalf + halfNum;
                start = start.next;
            }
            if (start.data % 2 == 1) {
                halfNum = (start.data - 1) / 2 + halfNum;
            } else {
                halfNum = start.data / 2 + halfNum;
            }

            LinkedList result = new LinkedList();
            char[] tempArray = halfNum.toCharArray();
            for (char c : tempArray) {
                result.insert(Integer.parseInt(String.valueOf(c)));
            }
            result.cleanZero();
            this.setHead(result.head);
        } else {
            System.out.println("This Linked List is not an even number");
        }
    }

    /*
    This function cleans the zero's in front
    Ex: Number "0012" will return "12"
    However, instead of working with a string, it is working with the LinkedList
     */

    public void cleanZero() {
        Node placeHolder = this.head;
        LinkedList result = new LinkedList();
        String valueString = this.getString();


        char[] tempArray = valueString.toCharArray();
        for (char c : tempArray) {
            result.insert(Integer.parseInt(String.valueOf(c)));
        }

        this.setHead(result.head);
    }

    /*
    This function takes the exponent of 2 linked List
    This time, the order is very important meaning the object calling the exponent
    is the base and the other has to be the exponent.
    I need to set up a situation for 0 ^ x = 0 or x ^ 0 = 1 or 0 ^ 0 = 1
    as these are special cases that need to account for.

    In order to do the calculation I will employ x^2^(n/2) over and over
    to simplify the process. I will also use x(x^2)^((n-1)/2) for odd exponents

    I will use the half() method to half the exponent each time if it is even
    At the end of the process the exponent LinkedList should be of length 1 and have a
    value of 1 meaning the base value should be the final result. However, in the case
    of odd exponents at the beginning or anytime during, a placeholder variable will store
    those numbers by multiplying them each time and finally multiplying the placeholder
    by the base for the final result.
     */
    public LinkedList exponent(LinkedList other) {

        LinkedList base = this;
        LinkedList expo = new LinkedList();

        if (other.head == null) {
            expo.insert(1);
            return expo;
        } else if (other.head.data == 0 && other.length() == 1) {
            expo.insert(1);
            return expo;
        } else {
            expo.setHead(other.head);
        }

        if (base.head == null) {
            base.insert(0);
            return base;
        }





        LinkedList placeHolder = new LinkedList();
        placeHolder.insert(1);

        while (expo.length() != 1 || expo.head.data > 1) {
            if (expo.head.data % 2 == 1) {
                placeHolder = placeHolder.multiply(base);
                expo.head.data--;
            }
            base = base.multiply(base);
            expo.half();
        }

        return placeHolder.multiply(base);
    }
}

