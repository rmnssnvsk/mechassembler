package model;

import model.builder.AbstractBodyBuilder;

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

    public Level(GoalListener goal) {
        bodies = new ArrayList<>();
        gravity = new Vector3f(0, -10, 0);
        this.goal = goal;
    }

    public Level(Vector3f gravity, GoalListener goal) {
        bodies = new ArrayList<>();
        this.gravity = gravity;
        this.goal = goal;
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
}
