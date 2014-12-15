package view.event;

import model.RunState;
import view.View;

/**
 * Created on 12/15/14.
 *
 * @author Mike Sorokin
 */
public class StateChangeRequestedViewEvent extends ViewEvent {
    public RunState state;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public StateChangeRequestedViewEvent(View source) {
        super(source);
    }

    public StateChangeRequestedViewEvent(View source, RunState state) {
        super(source);
        this.state = state;
    }
}
