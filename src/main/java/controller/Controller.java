package controller;

import com.bulletphysics.collision.dispatch.CollisionWorld;
import model.Model;
import model.RunState;
import model.builder.BodyBuilder;
import view.View;
import view.event.CloseRequestedViewEvent;
import view.event.PropertyChangeRequestViewEvent;
import view.event.StateChangeRequestedViewEvent;
import view.event.ViewEvent;

import javax.vecmath.Vector3f;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Класс контроллера. Контроллер обновляет {@link model.Model модель}
 * и визуализирует ее с помощью {@link view.View представления}
 *
 * @author Mike Sorokin
 */
public class Controller implements Observer {
    private Model model;
    private View view;
    private RunState state;

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
                } else if (e instanceof PropertyChangeRequestViewEvent) {
                    Vector3f begin = view.getCamera().getPos();
                    Vector3f rot = (Vector3f) view.getCamera().getRot().clone();
                    rot.scale((float) (Math.PI / 180));
                    Vector3f end = new Vector3f(
                            (float) (Math.cos(rot.x) * Math.sin(rot.y)),
                            (float) -Math.sin(rot.x),
                            (float) -(Math.cos(rot.x) * Math.cos(rot.y))
                    );
                    end = new Vector3f(begin.x + 1000 * end.x, begin.y + 1000 * end.y, begin.z + 1000 * end.z);
                    CollisionWorld.ClosestRayResultCallback callback = new CollisionWorld.ClosestRayResultCallback(begin, end);
                    model.getWorld().rayTest(begin, end, callback);
                    if (callback.hasHit()) {
                        ((BodyBuilder) callback.collisionObject.getUserPointer()).setRestitution(1);
                        System.out.printf("Setting rest to 1.\n");
                    }
                } else if (e instanceof StateChangeRequestedViewEvent) {
                    StateChangeRequestedViewEvent event = (StateChangeRequestedViewEvent) e;
                    if (event.state != model.getRunState()) {
                        model.setRunState(event.state);
                        view.setConfEnabled(event.state == RunState.CONF);
                    }
                }
            });
        }
    }
}
