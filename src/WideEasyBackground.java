import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The WideEasyBackground class represents the background of the "Green 3" level.
 */
public class WideEasyBackground implements Sprite {
    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 600;

    @Override
    public void drawOn(DrawSurface d) {
        // Draw the sky background
        d.setColor(Color.white);
        d.fillRectangle(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        // Draw the sun rays
        d.setColor(Color.YELLOW);
        int centerX = 100;
        int centerY = 100;
        for (int ray = 0; ray < 45; ray++) {
            // Draw the ray
            d.drawLine(centerX, centerY, ray * 20, 300);
        }
        // Draw the sun
        d.setColor(Color.YELLOW.brighter());
        d.fillCircle(100, 100, 40);
        d.setColor(Color.yellow.darker());
        d.fillCircle(100, 100, 30);
        d.setColor(Color.YELLOW);
        d.fillCircle(100, 100, 15);
    }

    @Override
    public void timePassed() {

    }

}
