package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection, is a clas that have a list of every sprite.
 */
public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * Constructor ,Creates a new instance of SpriteCollection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Add the given sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Gets sprites.
     *
     * @return the sprites
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Calls the timePassed() method on all sprites in the collection.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s:spritesCopy) {
            s.timePassed();
        }
    }

    /**
     * Calls the drawOn(d) method on all sprites in the collection.
     *
     * @param d the DrawSurface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s: sprites) {
            s.drawOn(d);
        }
    }
}