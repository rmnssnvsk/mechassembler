package view.event;

import view.View;

/**
 * Created on 10/26/14.
 *
 * @author Mike Sorokin
 */
public class CloseRequestedViewEvent extends ViewEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CloseRequestedViewEvent(View source) {
        super(source);
    }
}
