/**
 * Daniel Chung
 * LAB 3
 *2/6/18
 */
package queues;

/**
 * This is the interface for the queues classes
 */

public interface IQueue<T> {

    int size();

    boolean isEmpty();

    T front();

    void enqueue(T goat);

    T dequeue();


}
