package queues;
import java.util.LinkedList;

public class LinkedQueue<T> implements IQueue {
    LinkedList<Object> mylist = new LinkedList<>();

    /**
     * This function returns the size
     * of the linked list
     * @return size.
     */
    public int size() {
        return mylist.size();
    }

    /**
     * This function checks if the array is empty.
     * @return condition
     */
    public boolean isEmpty() {
        boolean condition = false;
        if (mylist.size() == 0) {
            condition = true;
        }
        return condition;
    }

    /**
     * This function gets the front
     * of the linked list
     * @return front
     */
    public Object front() {
        return mylist.getFirst();
    }

    /** this function enqueues an item
     * @param goat
     */
    public void enqueue(Object goat) {
        mylist.add(goat);
    }

    /**
     * This function dequeues an item and then returns it.
     * @return
     */
    public Object dequeue() {
        Object first = null;
        if (mylist.size() != 0){
            first = mylist.getFirst();
            mylist.removeFirst();
        }
        return first;
    }

}
