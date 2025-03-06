/**
 * The interface Collidable defines the behavior of objects that can be collided with in a game.
 */
public interface Collidable {
    /**
     * Gets the collision rectangle of the object.
     * @return the collision rectangle of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred with it at a given collision point and with a given velocity.
     * @param hitter the ball that hits.
     * @param collisionPoint the point at which the collision occurred.
     * @param currentVelocity the velocity with which the collision occurred.
     * @return the new velocity expected after the hit, based on the force the object inflicted on us.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}