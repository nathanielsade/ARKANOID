package collision;
import objects.Ball;
import objects.Block;
/**
 * The interface HitListener represents an object that listens to hit events.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the object that was hit
     * @param hitter   the ball that is doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}