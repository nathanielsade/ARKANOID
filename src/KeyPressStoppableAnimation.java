import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * The KeyPressStoppableAnimation class allows for stopping an animation by
 * pressing a specific key.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboardSensor;
    private final String key;
    private final Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed = true;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor the sensor
     * @param key the key that stop the animation
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.keyboardSensor = sensor;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (!isAlreadyPressed && keyboardSensor.isPressed(key)) {
            stop = true;
        }
        if (!keyboardSensor.isPressed(key)) {
            isAlreadyPressed = false;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}