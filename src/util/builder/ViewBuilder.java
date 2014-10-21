package util.builder;

import org.lwjgl.opengl.DisplayMode;
import view.View;

/**
 * Created on 10/21/14.
 *
 * @author Mike Sorokin
 */
public class ViewBuilder {
    private int x = 800;
    private int y = 600;
    private boolean fullscreen = false;
    private boolean vSync = false;
    private boolean resizable = false;
    private String title = "Window";

    public ViewBuilder setX(int x) {
        this.x = x;
        return this;
    }

    public ViewBuilder setY(int y) {
        this.y = y;
        return this;
    }

    public ViewBuilder setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
        return this;
    }

    public ViewBuilder setVSync(boolean vSync) {
        this.vSync = vSync;
        return this;
    }

    public ViewBuilder setResizable(boolean resizable) {
        this.resizable = resizable;
        return this;
    }

    public ViewBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public View build() {
        return new View(new DisplayMode(x, y), fullscreen, vSync, resizable, title);
    }
}
