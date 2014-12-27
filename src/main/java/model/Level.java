package model;

import model.builder.AbstractBodyBuilder;
import view.Camera;

import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 12/15/14.
 *
 * @author Mike Sorokin
 */
public class Level {
    private List<AbstractBodyBuilder> bodies;
    private Vector3f gravity;
    private GoalListener goal;
    private Camera camera;

    public Level(Vector3f gravity, GoalListener goal, Camera camera) {
        bodies = new ArrayList<>();
        this.gravity = gravity;
        this.goal = goal;
        this.camera = camera;
    }

    public void addBody(AbstractBodyBuilder body) {
        bodies.add(body);
    }

    public void removeBody(AbstractBodyBuilder body) {
        bodies.remove(body);
    }

    public List<AbstractBodyBuilder> getBodies() {
        return bodies;
    }

    public Vector3f getGravity() {
        return gravity;
    }

    public GoalListener getGoal() {
        return goal;
    }

    public Camera getCamera() {
        return camera;
    }
}
