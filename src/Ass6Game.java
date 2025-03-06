/**
 * @author Nethaniel Sade
 * @version 19.0.2
 * @since 2023-05-18
 * The type Main.
 */

import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.List;

/**
 * The Ass6Game class represents the entry point of the game application.
 * It creates and runs the game.
 */
public class Ass6Game {
    /**
     * Sorts the levels based on the command-line arguments.
     *
     * @param args the command-line arguments
     * @return the list of sorted level information
     */
    public static List<LevelInformation> sortByArgs(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        for (String arg : args) {
                if (arg.equals("1")) {
                    levels.add(new DirectHit());
                }
                if (arg.equals("2")) {
                    levels.add(new WideEasy());
                }
                if (arg.equals("3")) {
                    levels.add(new Green3());
                }
            }
        return levels;
    }

    /**
     * The main method is the entry point of the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        AnimationRunner animationRunner = new AnimationRunner();
        KeyboardSensor keyboardSensor = animationRunner.getGui().getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor);
        // Create the list of level information
        List<LevelInformation> levels = List.of(new DirectHit(), new WideEasy(), new Green3());
        // in case the arguments are empty, run the application in the regular order
        if (args.length > 0) {
            levels = sortByArgs(args);
            if (levels.isEmpty()) {
                levels = List.of(new DirectHit(), new WideEasy(), new Green3());
            }
        }
        gameFlow.runLevels(levels);
    }
}
