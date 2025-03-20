package levels;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import objects.Block;
import sprites.Sprite;
import physics.Velocity;
import geometry.Point;
import geometry.Rectangle;
import backgrounds.Green3Back;

/**
 * The Green3 class represents a specific level in the game.
 * It implements the LevelInformation interface to provide the necessary
 * information about the level.
 */
public class Green3 implements LevelInformation {
    private static final int NUM_OF_BLOCK_LINES = 5;
    private static final int NUM_OF_BLOCK = 10;
    private static final double BOARD_WIDTH = 800;

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(-3, -4));
        velocities.add(new Velocity(3, -4));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Green3Back();
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.blue.darker(), Color.white};
        int sideBlockWidth = 25;
        int blockWidth = 50;
        int blockHeight = 25;
        List<Block> blocks = new ArrayList<>();
        //those loop responsible for creating the blocks
        for (int i = 0; i < NUM_OF_BLOCK_LINES; i++) {
            for (int j = 0; j < NUM_OF_BLOCK - i; j++) {
                Block newBlock = new Block(new Rectangle(new Point(BOARD_WIDTH - sideBlockWidth
                        - ((j + 1) * blockWidth), 200 + (i * blockHeight)),
                        blockWidth, blockHeight), colors[i]);
                blocks.add(newBlock);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
