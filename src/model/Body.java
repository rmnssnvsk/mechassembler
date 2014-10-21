package model;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.MotionState;
import org.newdawn.slick.UnicodeFont;
import util.DisplayList;

import javax.vecmath.Vector3f;

/**
 * Created on 10/21/14.
 *
 * @author Mike Sorokin
 */
public class Body {
    private RigidBody rigidBody;
    private DisplayList list;

    public Body(RigidBody rigidBody, DisplayList list) {
        this.rigidBody = rigidBody;
        this.list = list;
    }

    public RigidBody getRigidBody() {
        return rigidBody;
    }

    public void setRigidBody(RigidBody rigidBody) {
        this.rigidBody = rigidBody;
    }

    public DisplayList getList() {
        return list;
    }

    public void setList(DisplayList list) {
        this.list = list;
    }

    public void delete() {
        this.rigidBody.destroy();
        this.list.delete();
    }
}
