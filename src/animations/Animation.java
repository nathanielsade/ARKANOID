package animations;

import biuoop.DrawSurface;
/**
 * The Animation interface represents an animation that can be displayed on a `DrawSurface`.
 * It defines methods for updating the animation and determining when it should stop.
 */
public interface Animation {
    /**
     * Perform one frame of the animation.
     *
     * @param d the `DrawSurface` to draw on
     */
    void doOneFrame(DrawSurface d);
    /**
     * Determine whether the animation should stop.
     *
     * @return `true` if the animation should stop, `false` otherwise
     */
    boolean shouldStop();
}

