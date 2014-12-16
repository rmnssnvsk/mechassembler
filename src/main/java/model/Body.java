package model;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.MotionState;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.opengl.Texture;
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
    public final String id;

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
