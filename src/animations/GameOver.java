package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The GameOver class represents the animation displayed when the game is over.
 */
public class GameOver implements Animation {
    private final KeyboardSensor keyboard;
    private final boolean stop;
    private final int count;

    /**
     * Creates a new GameOver animation.
     *
     * @param k     The keyboard sensor used to monitor keyboard inputs.
     * @param count The player's score.
     */
    public GameOver(KeyboardSensor k, int count) {
        this.keyboard = k;
        this.stop = false;
        this.count = count;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //show the game over screen
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + count, 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}