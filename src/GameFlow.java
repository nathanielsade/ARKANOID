import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The GameFlow class is responsible for managing the flow of the game.
 * It handles the progression through different levels, tracks the player's score,
 * and displays appropriate screens such as game over or victory screens.
 */
public class GameFlow {
    private final KeyboardSensor keyboardSensor;
    private final AnimationRunner animationRunner;
    private final Counter totalScore;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar The AnimationRunner used to run the game animations.
     * @param ks The KeyboardSensor used to monitor keyboard inputs.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.totalScore = new Counter(0);
    }

    /**
     * Runs the specified list of levels.
     *
     * @param levels The list of LevelInformation objects representing the levels to be played.
     */
    public void runLevels(List<LevelInformation> levels) {
        //this flag checks what is the reason the level has stopped
        int flag = 0;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.totalScore);
            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }
            //if no balls left the end game screen should appear
            if (level.getNumBalls() == 0) {
                Animation gameOverScreen = new GameOver(this.keyboardSensor, this.totalScore.getValue());
                KeyPressStoppableAnimation keyPressStoppableAnimation = new KeyPressStoppableAnimation(
                        this.keyboardSensor, KeyboardSensor.SPACE_KEY, gameOverScreen);
                this.animationRunner.run(keyPressStoppableAnimation);
                flag++;
                break;
            }
        }
        //if the while loop stop because the player finished all the level
        //show the win screen
        if (flag == 0) {
            Animation youWinScreen = new YouWin(this.keyboardSensor, this.totalScore.getValue());
            KeyPressStoppableAnimation keyPressStoppableAnimation =
                    new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY, youWinScreen);
            this.animationRunner.run(keyPressStoppableAnimation);
        }
        this.animationRunner.getGui().close();
    }
}