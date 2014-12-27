package util;

import model.Material;
import org.newdawn.slick.opengl.Texture;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 10.05.2014.
 *
 * @author Mike Sorokin
 */
public class TextureLoader {
    private static final Map<String, Texture> cache = new HashMap<>();
    public static final String NO_TEXTURE = "textures/notexture.png";

    public static Texture load(String name) {
        try {
            if (name.equals("")) {
                return load(NO_TEXTURE);
            }
            if (cache.containsKey(name)) {
                return cache.get(name);
            }
            Texture texture = org.newdawn.slick.opengl.TextureLoader.getTexture("PNG", new ResourceLoader(name).getURL().openStream());
            cache.put(name, texture);
            return texture;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
