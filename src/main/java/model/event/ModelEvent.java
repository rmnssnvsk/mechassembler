package model.event;

import model.Model;

import java.util.EventObject;

/**
 * Created on 10/26/14.
 *
 * @author Mike Sorokin
 */
public class ModelEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ModelEvent(Model source) {
        super(source);
    }
}
