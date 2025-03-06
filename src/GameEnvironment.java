import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class represents the environment of the game, which
 * consists of collidable objects.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Constructs a new GameEnvironment object.
     */
//constructor
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds the given collidable object to the environment.
     *
     * @param c the collidable object to add.
     */
    public void addCollidable(Collidable c) {
        if (c == null) {
            return;
        }
        this.collidables.add(c);
    }

    /**
     * Gets collidables.
     *
     * @return the collidables
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Returns information about the closest collision point between a given
     * trajectory and any of the collidable objects
     * in the environment.
     *
     * @param trajectory the trajectory to check collisions for.
     * @return the CollisionInfo object containing information about the closest
     * collision point, or null if no collision occurs.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double closestDistance = Double.POSITIVE_INFINITY;
        CollisionInfo closestCollision = null;
        // iterate over all collidable objects and check if they collide with the trajectory
        for (Collidable c : collidables) {
            Rectangle object = c.getCollisionRectangle();
            Point intersection = trajectory.closestIntersectionToStartOfLine(object);
            if (intersection != null) {
                // calculate distance from start of the trajectory to the intersection point
                double distance = trajectory.start().distance(intersection);
                if (distance < closestDistance) {
                    //update the closestDistance and the collision info
                    closestDistance = distance;
                    closestCollision = new CollisionInfo(intersection, c);
                }
            }
        }
        // if no collision was found, return null, otherwise return the closest collision
        if (closestCollision == null) {
            return null;
        } else {
            return closestCollision;
        }
    }
}