package collision;
import utils.Counter;
import objects.Ball;
import objects.Block;

/**
 * The type Score tracking listener is responsible for tracking and updating
 * the score when blocks are hit.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter to track
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Increase the score by 5 when a block is hit
        currentScore.increase(5);
    }
}