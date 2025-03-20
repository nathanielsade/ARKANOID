package levels;

import java.util.List;
import physics.Velocity;
import objects.Block;
import sprites.Sprite;

/**
 * The LevelInformation interface represents the information and characteristics of a game level.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return The number of balls.
     */
    int numberOfBalls();

    /**
     * Returns a list of the initial velocities for each ball in the level.
     * The size of the list should be equal to the number of balls.
     *
     * @return The list of initial ball velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle.
     *
     * @return The paddle speed.
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle.
     *
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     *
     * @return The level name.
     */
    String levelName();

    /**
     * Returns the background sprite of the level.
     *
     * @return The background sprite.
     */
    Sprite getBackground();

    /**
     * Returns a list of the blocks that make up the level.
     *
     * @return The list of blocks.
     */

    List<Block> blocks();

    /**
     * Returns the number of blocks that should be removed to clear the level.
     *
     * @return The number of blocks to remove.
     */
    int numberOfBlocksToRemove();
}