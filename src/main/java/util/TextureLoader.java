package util;

import org.newdawn.slick.opengl.Texture;

import java.io.IOException;

/**
 * Created on 10.05.2014.
 * @author Mike Sorokin
 */
public class TextureLoader {
    public static final Texture NO_TEXTURE = load("notexture");

    public static Texture load(String name) {
        try {
            return org.newdawn.slick.opengl.TextureLoader.getTexture("PNG", new ResourceLoader("textures/" + name + ".png").getURL().openStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
