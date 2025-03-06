import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Score indicator is a sprite that displays the current score on the screen.
 */
public class ScoreIndicator implements Sprite {
    public static final int BOARD_WIDTH = 800;
    private final Counter score;
    private final String name;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score counter
     * @param name  the name
     */
    public ScoreIndicator(Counter score, String name) {
        this.score = score;
        this.name = name;
    }
    @Override
    public void drawOn(DrawSurface d) {
        Block top = new Block(new Rectangle(new Point(0, 0), BOARD_WIDTH, 20),
                Color.white);
        d.setColor(Color.white);
        d.fillRectangle(0, 0, BOARD_WIDTH, 25);
        d.setColor(Color.black);
        d.drawText(350, 20, "Score: " + score.getValue(), 15);
        d.drawText(550, 20, "Level Name: " + this.name, 15);
    }

    @Override
    public void timePassed() {
        //TODO
    }

    /**
     * Adds the score indicator to the game.
     *
     * @param game the game to add the score indicator to
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
