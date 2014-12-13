package view.event;

import view.View;

/**
 * Created on 12/7/14.
 *
 * @author Mike Sorokin
 */
public class PropertyChangeRequestViewEvent extends ViewEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public PropertyChangeRequestViewEvent(View source) {
        super(source);
    }
}
