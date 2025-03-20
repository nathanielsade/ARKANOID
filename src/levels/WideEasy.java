package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import objects.Block;
import sprites.Sprite;
import physics.Velocity;
import geometry.Point;
import geometry.Rectangle;
import backgrounds.WideEasyBackground;

/**
 * The WideEasy class represents a specific level in the game.
 * It implements the LevelInformation interface to provide the necessary information about the level.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(-50 + i * 10, 5));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 20;
    }

    @Override
    public int paddleWidth() {
        return 550;
    }

    @Override
    public String levelName() {
        return "WideEasy";
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.yellow, Color.yellow,
                Color.GREEN, Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK, Color.PINK,
                Color.CYAN, Color.CYAN};
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Block newBlock = new Block(new Rectangle(new Point(25 + (50 * i),
                    300), 50, 20), colors[i]);
            blocks.add(newBlock);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
