package model.event;

import model.Model;

/**
 * Created on 10/26/14.
 *
 * @author Mike Sorokin
 */
public class GoalReachedModelEvent extends ModelEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public GoalReachedModelEvent(Model source) {
        super(source);
    }
}
