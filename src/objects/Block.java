package objects;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import geometry.Point;
import geometry.Rectangle;
import physics.Velocity;
import collision.HitListener;
import collision.HitNotifier;
import physics.Collidable;
import sprites.Sprite;
import game.GameLevel;

/**
 * The Block class represents a collidable block that can be drawn on a DrawSurface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Color color;
    private final Rectangle rectangle;
    private List<HitListener> hitListeners;


    /**
     * Instantiates a new Block.
     *
     * @param rect  the Rectangle that defines the block.
     * @param color the color of the block
     */
    public Block(Rectangle rect, Color color) {
        this.rectangle = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Instantiates a new Block.
     *
     * @param rectangle the rectangle that defines the block
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

   @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (currentVelocity == null) {
            return null;
        }
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        //check which line it hits
        //if it's the upper or lower line change
        if (this.rectangle.getUpperLine().inLine(collisionPoint) && dy < 0
                || this.rectangle.getLowerLine().inLine(collisionPoint) && dy > 0) {
            dy = -dy;
        }
        //if it's right or left line
        if (this.rectangle.getLeftLine().inLine(collisionPoint) && dx > 0
                || this.rectangle.getRightLine().inLine(collisionPoint) && dx < 0) {
            dx = -dx;
        }
        notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * Draw the block on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) ((int) this.rectangle.getUpperLeft().getY() - this.rectangle.getHeight()),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        //this black block is not part of the game , it's just to mark the borders
        d.setColor(Color.black);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) ((int) this.rectangle.getUpperLeft().getY() - this.rectangle.getHeight()),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {
        //TODO for now nothing
    }

    /**
     * Adds the block to a given game, to the sprite and collidable lists.
     *
     * @param g the game to add the block to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notifies the block that it has been hit by a ball.
     *
     * @param hitter the ball that hit the block
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


    /**
     * Removes the block from the game, including the sprite and collidable lists.
     *
     * @param game the game to remove the block from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
}
