import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The GameLevel class represents a single level in the game.
 * It initializes the game environment, creates game objects such as blocks, paddle, and balls,
 * and runs the game loop.
 */
public class GameLevel implements Animation {
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 600;
    public static final int TOP_BLOCK_HEIGHT = 25;
    public static final int SIDE_BLOCK_WIDTH = 25;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Paddle paddle;
    private final biuoop.KeyboardSensor keyboard;
    private final Counter blockCounter;
    private final Counter ballCounter;
    private final Counter scoreCounter;
    private final AnimationRunner runner;
    private boolean running = true;
    private final LevelInformation levelInformation;

    /**
     * Constructs a new GameLevel.
     *
     * @param levelInformation The level information for the current level.
     * @param keyboardSensor   The keyboard sensor used to monitor keyboard inputs.
     * @param animationRunner  The animation runner used to run the game animations.
     * @param scoreCounter     The counter for the player's score.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter scoreCounter) {
        this.levelInformation = levelInformation;
        this.runner = animationRunner;
        this.sprites = new SpriteCollection();
        if (levelInformation.getBackground() != null) {
            this.sprites.addSprite(levelInformation.getBackground());
        }
        this.environment = new GameEnvironment();
        this.keyboard = keyboardSensor;
        this.paddle = new Paddle(keyboard, new Rectangle(new Point((int)
                ((BOARD_WIDTH - this.levelInformation.paddleWidth()) / 2),
                575), this.levelInformation.paddleWidth(), 20));
        this.blockCounter = new Counter(0);
        this.ballCounter = new Counter(this.levelInformation.numberOfBalls());
        this.scoreCounter = scoreCounter;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //if no balls left then it should stop
        if (this.ballCounter.getValue() == 0) {
            this.running = false;
        }
        //if no blocks left the score needs to increase in100 point
        if (this.blockCounter.getValue() == 0) {
            this.scoreCounter.increase(100);
            this.running = false;
        }
        //drawing the sprites
        d.setColor(Color.blue);
        d.fillRectangle(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        //check if the pause screen should appear
        if (this.keyboard.isPressed("p")) {
            Animation pauseScreen = new PauseScreen(this.keyboard);
            Animation keyPressStoppableAnimation =
                    new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, pauseScreen);
            this.runner.run(keyPressStoppableAnimation);
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Gets game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGameEnviroment() {
        return this.environment;
    }

    /**
     * Add collidable.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Get num balls int.
     *
     * @return the int
     */
    public int getNumBalls() {
        return this.ballCounter.getValue();
    }

    /**
     * Initialize a new game: create the Blocks and listeners
     * and add them to the game.
     */
    public void initialize() {
        //creates listeners
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(scoreCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        // Add sprites to the game

        paddle.addToGame(this);
        // Add borders to the game
        Block top = new Block(new Rectangle(new Point(0, TOP_BLOCK_HEIGHT + 20), BOARD_WIDTH, TOP_BLOCK_HEIGHT),
                Color.DARK_GRAY);
        Block left = new Block(new Rectangle(new Point(0, BOARD_HEIGHT), SIDE_BLOCK_WIDTH, BOARD_HEIGHT),
                Color.DARK_GRAY);
        Block bottom = new Block(new Rectangle(new Point(SIDE_BLOCK_WIDTH, 625), 750, TOP_BLOCK_HEIGHT),
                Color.DARK_GRAY);
        Block right = new Block(new Rectangle(new Point(BOARD_WIDTH - SIDE_BLOCK_WIDTH, BOARD_HEIGHT),
                SIDE_BLOCK_WIDTH, BOARD_HEIGHT), Color.DARK_GRAY);
        bottom.addHitListener(ballRemover);
        top.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);
        bottom.addToGame(this);
        //add the block of the specific level to the game
        for (int i = 0; i < this.levelInformation.numberOfBlocksToRemove(); i++) {
            Block block = this.levelInformation.blocks().get(i);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            blockCounter.increase(1);
            block.addToGame(this);
        }

        // Create the ScoreIndicator and add it as a sprite to the game
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCounter,
                this.levelInformation.levelName());
        scoreIndicator.addToGame(this);
    }

    /**
     * Create balls on top of paddle.
     */
    public void createBallsOnTopOfPaddle() {

        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(400, 500, 5, Color.white);
            ball.addToGame(this);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
        }
    }

    /**
     * Run the game and start the animation loop.
     */
    public void run() {
        this.createBallsOnTopOfPaddle(); // or a similar method
        Color countdownColor = Color.BLACK;
        if (this.levelInformation.levelName().equals("Direct Hit")) {
            countdownColor = Color.white;
        }
        //countdown before turn starts.
        this.runner.run(new CountdownAnimation(3, 2,
                this.sprites, countdownColor));
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }
}