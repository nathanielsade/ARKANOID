package collision;

import game.GameLevel;
import utils.Counter;
import objects.Ball;
import objects.Block;

/**
 * The BlockRemover class is responsible for removing blocks from the game and
 * keeping track of the remaining blocks.
 * It implements the HitListener interface.
 */
public class BlockRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * Constructs a new BlockRemover with the given game and counter.
     *
     * @param game          the game
     * @param removedBlocks the counter for removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}