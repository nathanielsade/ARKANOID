import biuoop.DrawSurface;
/**
 * The Sprite interface represents a game element that can be drawn on the
 * screen and has an update function.
 **/
public interface Sprite {
    /**
     * Draw the sprite on the given draw surface.
     *
     * @param d the draw surface
     */
    void drawOn(DrawSurface d);
    /**
     * Notify the sprite that a unit of time has passed.
     */
    void timePassed();
}