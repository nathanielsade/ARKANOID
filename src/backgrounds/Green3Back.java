package backgrounds;

import biuoop.DrawSurface;

import java.awt.Color;
import geometry.Rectangle;
import sprites.Sprite;

/**
 * The Green3Back class represents the background of the "Green 3" level.
 */
public class Green3Back implements Sprite {
    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 600;

    @Override
    public void drawOn(DrawSurface d) {
        // Draw the background
        d.setColor(Color.green.darker().darker());
        d.fillRectangle(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        // Draw the building
        d.setColor(Color.black.brighter());
        d.fillRectangle(50, 400, 100, 400);
        //draw the windows
        d.setColor(Color.WHITE);
        int windowWidth = 18;
        int windowHeight = 30;
        int windowGapX = 6;
        int windowGapY = 9;
        int windowStartX = 55;
        int windowStartY = 405;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                int x = windowStartX + (windowWidth + windowGapX) * col;
                int y = windowStartY + (windowHeight + windowGapY) * row;
                d.fillRectangle(x, y, windowWidth, windowHeight);
            }
        }
        // Draw the antenna
        d.setColor(Color.gray.darker().darker());
        int antennaWidth = 30;
        int antennaHeight = 20;
        int antennaX = 85;
        int antennaY = 380;
        d.fillRectangle(antennaX, antennaY, antennaWidth, antennaHeight);
        d.setColor(Color.gray.darker());
        antennaWidth = 10;
        antennaHeight = 130;
        antennaX = 95;
        antennaY = 250;
        d.fillRectangle(antennaX, antennaY, antennaWidth, antennaHeight);
        // Draw the circle
        d.setColor(Color.red.brighter());
        d.fillCircle(100, 250, 15);
        d.setColor(Color.pink);
        d.fillCircle(100, 250, 10);
        d.setColor(Color.white);
        d.fillCircle(100, 250, 5);
    }

    @Override
    public void timePassed() {
        //Todo
    }
}
