import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The AnimationRunner class is responsible for running an animation loop
 * and displaying the animation on the screen.
 */
public class AnimationRunner {
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 600;
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * Creates a new AnimationRunner instance.
     */
    AnimationRunner() {
        this.gui = new GUI("Arkanoid", BOARD_WIDTH, BOARD_HEIGHT);
        this.sleeper = new Sleeper();
        this.framesPerSecond = 60;
    }

    /**
     * Returns the GUI object associated with the AnimationRunner.
     *
     * @return the GUI object
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Runs the animation loop and displays the animation on the screen.
     *
     * @param animation the animation to run
     */
    public void run(Animation animation) {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}