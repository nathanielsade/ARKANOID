package collision;


/**
 * The interface HitNotifier represents an object that can notify listeners of hit events.
 */
public interface HitNotifier {
    /**
     * Add a hit listener to the notifier.
     *
     * @param hl the hit listener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove a hit listener from the notifier.
     *
     * @param hl the hit listener to remove
     */
    void removeHitListener(HitListener hl);
}