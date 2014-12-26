package model;

import com.bulletphysics.linearmath.Transform;
import model.builder.BoxBodyBuilder;

import javax.vecmath.Vector3f;
import java.util.List;

/**
 * Created on 12/16/14.
 *
 * @author Mike Sorokin
 */
public class GoalListener {
    private final String id1, id2;
    private final float dist, time;
    private float timeLeft;

    public GoalListener(String id1, String id2, float distance, float time) {
        this.id1 = id1;
        this.id2 = id2;
        this.dist = distance;
        this.time = time;
        this.timeLeft = time;
    }

    public boolean isGoalReached(List<Body> bodies, float t) {
        Body body1 = null, body2 = null;
        for (Body b : bodies) {
            if (b.id.equals(id1)) {
                body1 = b;
            }
            if (b.id.equals(id2)) {
                body2 = b;
            }
        }
        Vector3f pos1 = body1.getRigidBody().getWorldTransform(new Transform()).origin;
        Vector3f pos2 = body2.getRigidBody().getWorldTransform(new Transform()).origin;
        Vector3f d = new Vector3f(pos1.x - pos2.x, pos1.y - pos2.y, pos1.z - pos2.z);
        if (d.length() <= dist) {
            timeLeft -= t;
        } else {
            timeLeft = time;
        }
        return timeLeft <= 0;
    }
}
