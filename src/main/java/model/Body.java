package model;

import com.bulletphysics.dynamics.RigidBody;
import util.DisplayList;

/**
 * Created on 10/21/14.
 *
 * @author Mike Sorokin
 */
public class Body {
    public final String id;
    private RigidBody rigidBody;
    private DisplayList list;

    public Body(RigidBody rigidBody, DisplayList list, String id) {
        this.rigidBody = rigidBody;
        this.list = list;
        this.id = id;
    }

    public RigidBody getRigidBody() {
        return rigidBody;
    }

    public DisplayList getList() {
        return list;
    }

    public void delete() {
        this.rigidBody.destroy();
        this.list.delete();
    }
}
