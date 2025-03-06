import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The PauseScreen class is responsible for displaying the pause screen during the game.
 */
public class PauseScreen implements Animation {
    private final KeyboardSensor keyboard;
    private final boolean stop;

    /**
     * Creates a new PauseScreen instance.
     *
     * @param k The KeyboardSensor used to detect key presses.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // Display the pause message on the screen
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}