package util;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static util.FlippedFloatBuffer.asFloatBuffer;

/**
 * Created on 30.08.2014.
 *
 * @author Mike Sorokin
 */
class MTL {
    HashMap<String, Style> styles;

    public MTL(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new ResourceLoader().getFile(filename)));
        String line;
        styles = new HashMap<>();
        String curStyleName;
        Style curStyle = null;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("newmtl ")) {
                curStyleName = line.split(" ")[1];
                styles.put(curStyleName, (curStyle = new Style()));
            } else if (line.startsWith("Ns ")) {
                assert curStyle != null;
                curStyle.Ns = Float.parseFloat(line.split(" ")[1]);
            } else if (line.startsWith("Ka ")) {
                String[] arg = line.split(" ");
                assert curStyle != null;
                curStyle.Ka.x = Float.parseFloat(arg[1]);
                curStyle.Ka.y = Float.parseFloat(arg[2]);
                curStyle.Ka.z = Float.parseFloat(arg[3]);
            } else if (line.startsWith("Kd ")) {
                String[] arg = line.split(" ");
                assert curStyle != null;
                curStyle.Kd.x = Float.parseFloat(arg[1]);
                curStyle.Kd.y = Float.parseFloat(arg[2]);
                curStyle.Kd.z = Float.parseFloat(arg[3]);
            } else if (line.startsWith("Ks ")) {
                String[] arg = line.split(" ");
                assert curStyle != null;
                curStyle.Ks.x = Float.parseFloat(arg[1]);
                curStyle.Ks.y = Float.parseFloat(arg[2]);
                curStyle.Ks.z = Float.parseFloat(arg[3]);
            } else if (line.startsWith("map_Kd ")) {
                assert curStyle != null;
                curStyle.map_Kd = org.newdawn.slick.opengl.TextureLoader.getTexture("PNG", new FileInputStream("res/models/" + line.split(" ")[1].replace("\\", "/")));
            }
        }
    }

    void useStyle(String name) {
        styles.get(name).use();
    }

    private static class Style {
        float Ns;
        Vector3f Ka, Kd, Ks;
        Texture map_Kd;

        private Style() {
            Ka = new Vector3f();
            Kd = new Vector3f();
            Ks = new Vector3f();
        }

        void use() {
            glMaterial(GL_FRONT, GL_AMBIENT, asFloatBuffer(Ka.x, Ka.y, Ka.z, 1));
            glMaterial(GL_FRONT, GL_DIFFUSE, asFloatBuffer(Kd.x, Kd.y, Kd.z, 1));
            glMaterial(GL_FRONT, GL_SPECULAR, asFloatBuffer(Ks.x, Ks.y, Ks.z, 1));
            glMaterialf(GL_FRONT, GL_SHININESS, Ns / 1000 * 128);
            if (map_Kd != null) {
                map_Kd.bind();
            } else {
                glBindTexture(GL_TEXTURE_2D, TextureLoader.NO_TEXTURE.getTextureID());
            }
        }
    }
}
