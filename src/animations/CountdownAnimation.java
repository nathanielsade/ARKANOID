package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;
import sprites.SpriteCollection;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private final int countDown;
    private final SpriteCollection gameScreen;
    private final long startTime;
    private final long countDuration;
    private boolean stop;
    private final Sleeper sleeper;
    private final Color color;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the number of seconds for the countdown
     * @param countFrom    the starting count value
     * @param gameScreen   the game screen to display during the countdown
     * @param color        the color of the countdown text
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen, Color color) {
        this.countDown = countFrom;
        this.gameScreen = gameScreen;
        this.startTime = System.currentTimeMillis();
        this.countDuration = (long) (numOfSeconds * 750) / countFrom;
        this.stop = false;
        this.sleeper = new Sleeper();
        this.color = color;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        d.setColor(Color.blue);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        // Draw the game screen
        this.gameScreen.drawAllOn(d);

        // Calculate current countdown number
        int currentNumber = this.countDown - (int) ((System.currentTimeMillis()
                - this.startTime) / this.countDuration) + 1;

        // Draw the countdown number or "GO"
        if (currentNumber > 0) {
            d.setColor(color);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(currentNumber), 32);
        } else {
            d.setColor(color);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "GO", 32);
        }

        // Sleep for frame duration
        this.sleeper.sleepFor(millisecondsPerFrame);

        // Check if countdown is finished
        if (currentNumber <= -1) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
