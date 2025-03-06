/**
 * The BallRemover class is responsible for removing balls from the game when they hit a block.
 * It implements the HitListener interface.
 */
public class BallRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBalls;

    /**
     * Constructs a new BallRemover with the given game and counter.
     *
     * @param game    the game
     * @param counter the counter for remaining balls
     */
    public BallRemover(GameLevel game, Counter counter) {
        this.game = game;
        this.remainingBalls = counter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        remainingBalls.decrease(1);
    }
}
