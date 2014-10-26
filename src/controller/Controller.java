package controller;

import model.Body;
import model.Model;
import view.View;
import view.event.CloseRequestedViewEvent;
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
            List<Body> bodies = (List<Body>) arg;
            view.show(bodies);
        } else {
            List<ViewEvent> events = (List<ViewEvent>) arg;
            events.stream().filter(event -> event instanceof CloseRequestedViewEvent).forEach(event -> {
                model.stop();
            });
        }
    }
}
