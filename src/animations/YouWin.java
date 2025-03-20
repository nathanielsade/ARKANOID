package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type You win.
 */
public class YouWin implements Animation {
    private final KeyboardSensor keyboard;
    private final boolean stop;
    private final int count;

    /**
     * Creates a new YouWin screen instance.
     *
     * @param k     The KeyboardSensor used to detect key presses.
     * @param count the count
     */
    public YouWin(KeyboardSensor k, int count) {
        this.keyboard = k;
        this.stop = false;
        this.count = count;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + count, 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}