package util;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 10.05.2014.
 * @author Mike Sorokin
 */
public class OBJModel {
    private ArrayList<Vector3f> vertices, normals;
    private ArrayList<Vector2f> textures;
    private ArrayList<Face> faces;
    private DisplayList list;
    private MTL mtl;

    private OBJModel() {
        vertices = new ArrayList<>();
        normals = new ArrayList<>();
        textures = new ArrayList<>();
        faces = new ArrayList<>();
    }

    public static OBJModel load(String name) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new ResourceLoader("res/models/" + name + ".obj").getFile()));
        OBJModel model = new OBJModel();
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim().replaceAll(" {2,}", " ");
            if (line.startsWith("mtllib ")) {
                model.mtl = new MTL(line.split(" ")[1]);
            } else if (line.startsWith("v ")) {
                String[] vrt = line.split(" ");
                float x = Float.valueOf(vrt[1]);
                float y = Float.valueOf(vrt[2]);
                float z = Float.valueOf(vrt[3]);
                model.vertices.add(new Vector3f(x, y, z));
            } else if (line.startsWith("vt ")) {
                String[] tex = line.split(" ");
                float x = Float.valueOf(tex[1]);
                float y = 1f - Float.valueOf(tex[2]);
                model.textures.add(new Vector2f(x, y));
            } else if (line.startsWith("vn ")) {
                String[] nml = line.split(" ");
                float x = Float.valueOf(nml[1]);
                float y = Float.valueOf(nml[2]);
                float z = Float.valueOf(nml[3]);
                model.normals.add(new Vector3f(x, y, z));
            } else if (line.startsWith("f ")) {
                String[] face = line.split(" ");
                int size = face.length - 1;
                int[] vertices = new int[size];
                int[] textures = new int[size];
                int[] normals = new int[size];
                boolean hasTextures = false;
                boolean hasNormals = false;
                for (int i = 0; i < size; i++) {
                    String[] v = face[i + 1].split("/");
                    vertices[i] = Integer.parseInt(v[0]) - 1;
                    if (!v[1].isEmpty()) {
                        textures[i] = Integer.parseInt(v[1]) - 1;
                        hasTextures = true;
                    }
                    if (v.length == 3) {
                        normals[i] = Integer.parseInt(v[2]) - 1;
                        hasNormals = true;
                    }
                }
                model.faces.add(new Face(vertices, hasTextures ? textures : null, hasNormals ? normals : null, ""));
            } else if (line.startsWith("usemtl ")) {
                model.faces.add(new Face(null, null, null, line.split(" ")[1]));
            }
        }
        reader.close();
        model.list = new DisplayList(() -> {
            for (Face face : model.faces) {
                if (face.vertices != null) {
                    glBegin(GL_POLYGON);
                    for (int i = 0; i < face.vertices.length; i++) {
                        if (face.normals != null) {
                            Vector3f nml = model.normals.get(face.normals[i]);
                            glNormal3f(nml.x, nml.y, nml.z);
                        }
                        if (face.textures != null) {
                            Vector2f tex = model.textures.get(face.textures[i]);
                            glTexCoord2f(tex.x, tex.y);
                        }
                        Vector3f vrt = model.vertices.get(face.vertices[i]);
                        glVertex3f(vrt.x, vrt.y, vrt.z);
                    }
                    glEnd();
                } else {
                    model.mtl.useStyle(face.style);
                }
            }
        });
        return model;
    }

    public void draw() {
        list.call();
    }

    public void delete() {
        list.delete();
    }
}
