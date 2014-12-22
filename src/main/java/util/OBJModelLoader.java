package util;

import com.bulletphysics.collision.shapes.CompoundShape;
import com.bulletphysics.collision.shapes.TriangleShape;
import com.bulletphysics.linearmath.Transform;
import model.builder.AbstractBodyBuilder;
import model.builder.DefaultBodyBuilder;
import model.builder.MaterialBuilder;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 10.05.2014.
 * @author Mike Sorokin
 */
public class OBJModelLoader {

    public static DefaultBodyBuilder load(String name, String id) {
        ArrayList<Vector3f> vertices = new ArrayList<>();
        ArrayList<Vector3f> normals = new ArrayList<>();
        ArrayList<Vector2f> textures = new ArrayList<>();
        ArrayList<Face> faces = new ArrayList<>();
        Runnable list;
        MTL mtl = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new ResourceLoader(name).getFile()));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim().replaceAll(" {2,}", " ");
                if (line.startsWith("mtllib ")) {
                    mtl = new MTL(line.split(" ")[1]);
                } else if (line.startsWith("v ")) {
                    String[] vrt = line.split(" ");
                    float x = Float.valueOf(vrt[1]);
                    float y = Float.valueOf(vrt[2]);
                    float z = Float.valueOf(vrt[3]);
                    vertices.add(new Vector3f(x, y, z));
                } else if (line.startsWith("vt ")) {
                    String[] tex = line.split(" ");
                    float x = Float.valueOf(tex[1]);
                    float y = 1f - Float.valueOf(tex[2]);
                    textures.add(new Vector2f(x, y));
                } else if (line.startsWith("vn ")) {
                    String[] nml = line.split(" ");
                    float x = Float.valueOf(nml[1]);
                    float y = Float.valueOf(nml[2]);
                    float z = Float.valueOf(nml[3]);
                    normals.add(new Vector3f(x, y, z));
                } else if (line.startsWith("f ")) {
                    String[] face = line.split(" ");
                    int size = face.length - 1;
                    int[] faceVertices = new int[size];
                    int[] faceTextures = new int[size];
                    int[] faceNormals = new int[size];
                    boolean hasTextures = false;
                    boolean hasNormals = false;
                    for (int i = 0; i < size; i++) {
                        String[] v = face[i + 1].split("/");
                        faceVertices[i] = Integer.parseInt(v[0]) - 1;
                        if (!v[1].isEmpty()) {
                            faceTextures[i] = Integer.parseInt(v[1]) - 1;
                            hasTextures = true;
                        }
                        if (v.length == 3) {
                            faceNormals[i] = Integer.parseInt(v[2]) - 1;
                            hasNormals = true;
                        }
                    }
                    faces.add(new Face(faceVertices, hasTextures ? faceTextures : null, hasNormals ? faceNormals : null, ""));
                } else if (line.startsWith("usemtl ")) {
                    faces.add(new Face(null, null, null, line.split(" ")[1]));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final MTL finalMtl = mtl;
        list = () -> {
            new MaterialBuilder().build().apply();
            glColor3f(1, 1, 1);
            glBindTexture(GL_TEXTURE_2D, TextureLoader.NO_TEXTURE.getTextureID());
            for (Face face : faces) {
                if (face.vertices != null) {
                    glBegin(GL_POLYGON);
                    for (int i = 0; i < face.vertices.length; i++) {
                        if (face.normals != null) {
                            Vector3f nml = normals.get(face.normals[i]);
                            glNormal3f(nml.x, nml.y, nml.z);
                        }
                        if (face.textures != null) {
                            Vector2f tex = textures.get(face.textures[i]);
                            glTexCoord2f(tex.x, tex.y);
                        }
                        Vector3f vrt = vertices.get(face.vertices[i]);
                        glVertex3f(vrt.x, vrt.y, vrt.z);
                    }
                    glEnd();
                } else {
                    assert finalMtl != null;
                    finalMtl.useStyle(face.style);
                }
            }
        };

        CompoundShape shape = new CompoundShape();
        Transform t = new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), new javax.vecmath.Vector3f(0, 0, 0), 1));
        for (Face face : faces) {
            if (face.vertices != null) {
                for (int i = 1; i < face.vertices.length - 1; i++) {
                    shape.addChildShape(t, new TriangleShape(
                            vertices.get(face.vertices[0]),
                            vertices.get(face.vertices[i]),
                            vertices.get(face.vertices[i + 1])
                    ));
                }
            }
        }

        return new DefaultBodyBuilder(id)
                .setCollisionShape(shape)
                .setDisplayList(list);
    }
}
