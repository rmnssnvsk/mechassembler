package controller;

import model.Model;
import view.View;
import view.event.CloseRequestedViewEvent;
import view.event.PropertyChangeRequestViewEvent;
import view.event.ViewEvent;

import java.util.*;

/**
 * Класс контроллера. Контроллер обновляет {@link model.Model модель}
 * и визуализирует ее с помощью {@link view.View представления}
 *
 * @author Mike Sorokin
 */
public class Controller implements Observer {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Model) {
            view.show(model.getBodies());
        } else {
            //noinspection unchecked
            List<ViewEvent> events = (List<ViewEvent>) arg;
            events.stream().forEach(e -> {
                if (e instanceof CloseRequestedViewEvent) {
                    model.stop();
                } else //noinspection StatementWithEmptyBody
                    if (e instanceof PropertyChangeRequestViewEvent) {
                    // TODO
                }
            });
        }
    }
}
