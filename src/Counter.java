/**
 * The Counter class represents a simple counter that can be increased or decreased.
 */
public class Counter {
    // add number to current count.
    private int number;

    /**
     * Instantiates a new Counter with the specified initial value.
     *
     * @param number the initial value of the counter
     */
    Counter(int number) {
        this.number = number;
    }

    /**
     * Increases the counter by the specified amount.
     *
     * @param number the amount to increase the counter by
     */
    void increase(int number) {
        this.number += number;
    }

    /**
     * Decreases the counter by the specified amount.
     *
     * @param number the amount to decrease the counter by
     */
    void decrease(int number) {
        this.number -= number;
    }

    /**
     * Gets the current value of the counter.
     *
     * @return the current value of the counter
     */
    int getValue() {
        return this.number;
    }
}