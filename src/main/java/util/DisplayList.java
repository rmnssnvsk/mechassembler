package util;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 17.05.2014.
 *
 * @author Mike Sorokin
 */
public class DisplayList {
    public final int list;
    public final Runnable code;

    public DisplayList(Runnable list) {
        this.code = list;
        this.list = glGenLists(1);
        glNewList(this.list, GL_COMPILE);
        list.run();
        glEndList();
    }

    public void call() {
        glCallList(list);
    }

    public void delete() {
        glDeleteLists(list, 1);
    }
}
