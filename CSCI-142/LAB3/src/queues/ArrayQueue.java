/**
 * Daniel Chung
 * LAB 3
 *2/6/18
 */
package queues;
import goats.BattleGoat;

import java.util.Arrays;

/***
 * Implementation of circular queues
 * @param <T>
 */
public class ArrayQueue<T> implements IQueue {
    private int front = 0;
    private int back = 0;
    private int size = 0;
    private Object[] array;

    /**
     * This function creates the array with the
     * desired lenght
     * @param size
     */
    public ArrayQueue(int size){
        this.size = size;
        array = (T[]) new Object[size];
    }

    /**
     * This function returns the size
     * of the array.
     * @return size
     */
    public int size() {
        return this.size;
    }

    /**
     * This function checks if the
     * queues is empty or not.
     * @return true if it's empty false if its not.
     */
    public boolean isEmpty() {
        boolean condition = false;
        if (this.size == 0){
            condition = true;
        }
        return condition;
    }

    /**
     * This function gets the front of
     * the queue
     * @return front of the queue.
     */
    public Object front() {
        return array[front];
    }

    /**
     * This function enqueue's to
     * the circular array.
     * @param goat
     */
    public void enqueue(Object goat) {
        back = (back + 1) % size;
        array[back] = goat;
        this.size++;
    }

    /**
     * This functions dequeues from the
     * circular array.
     * @return
     */
    public Object dequeue() {
        front = (front + 1) % size;
        this.size--;
        return array[front];
    }
}
