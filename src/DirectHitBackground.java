import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The class Direct hit background is responsible for drawing the background
 * of a specific scene.
 */
public class DirectHitBackground implements Sprite {
    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 600;
    private static final int CENTER_X = 400;
    private static final int CENTER_Y = 200;
    private static final int TARGET_RADIUS = 100;

    @Override
    public void drawOn(DrawSurface d) {
        // Set the background color
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        // Draw the target
        d.setColor(Color.BLUE);
        d.drawCircle(CENTER_X, CENTER_Y, TARGET_RADIUS);
        d.drawCircle(CENTER_X, CENTER_Y, 50);
        d.drawCircle(CENTER_X, CENTER_Y, 150);
        d.drawLine(CENTER_X, 375, CENTER_X, 25);
        d.drawLine(225, 200, 575, 200);
    }

    @Override
    public void timePassed() {
        //TODO
    }
}
