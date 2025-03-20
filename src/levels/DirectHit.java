package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import objects.Block;
import sprites.Sprite;
import physics.Velocity;
import geometry.Point;
import geometry.Rectangle;
import backgrounds.DirectHitBackground;

/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -4));
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new DirectHitBackground();

    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(375, 225), 50, 50),
                Color.RED));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
