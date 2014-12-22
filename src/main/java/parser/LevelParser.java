package parser;

import model.Level;
import model.Material;
import model.builder.*;
import org.newdawn.slick.opengl.Texture;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import parser.parsetypes.*;
import util.OBJModelLoader;
import util.TextureLoader;

import javax.vecmath.Vector3f;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LevelParser {
    private static Map<String, Material> materials;
    private static Map<String, Texture> textures;

    private static Vector3f toVector3f(ParseVectorXYZ v) {
        return new Vector3f(v.x, v.y, v.z);
    }

    private static Vector3f toVector3f(ParseVectorRGB v) {
        return new Vector3f(v.r, v.g, v.b);
    }

    private static Material toMaterial(ParseMaterial parseMaterial) {
        MaterialBuilder builder = new MaterialBuilder();
        if (parseMaterial.ambient != null) {
            builder.setAmbient(toVector3f(parseMaterial.ambient));
        }
        if (parseMaterial.diffuse != null) {
            builder.setDiffuse(toVector3f(parseMaterial.diffuse));
        }
        if (parseMaterial.specular != null) {
            builder.setSpecular(toVector3f(parseMaterial.specular));
        }
        if (parseMaterial.shininess != null) {
            builder.setShininess(parseMaterial.shininess);
        }
        return builder.build();
    }

    private static Texture toTexture(ParseTexture parseTexture) {
        return TextureLoader.load(parseTexture.filename);
    }

    private static DefaultBodyBuilder toBoxBodyBuilder(ParseBox body) {
        BoxBodyBuilder builder = new BoxBodyBuilder(body.id);
        if (body.texture != null) {
            builder.setTexture(textures.get(body.texture));
        }
        if (body.material != null) {
            builder.setMaterial((materials.get(body.material)));
        }
        if (body.size != null) {
            builder.setSize(toVector3f(body.size));
        }
        if (body.color != null) {
            builder.setColor(toVector3f(body.color));
        }
        return builder;
    }

    private static DefaultBodyBuilder toOBJBodyBuilder(ParseOBJModel body) {
        DefaultBodyBuilder builder = OBJModelLoader.load(body.filename, body.id);
        return builder;
    }

    private static DefaultBodyBuilder toPlaneBodyBuilder(ParsePlane body) {
        PlaneBodyBuilder builder = new PlaneBodyBuilder(body.id);
        if (body.color != null) {
            builder.setColor(toVector3f(body.color));
        }
        if (body.material != null) {
            builder.setMaterial(materials.get(body.material));
        }
        if (body.normal != null) {
            builder.setNormal(toVector3f(body.normal));
        }
        if (body.size != null) {
            builder.setSize(body.size);
        }
        if (body.distance != null) {
            builder.setDst(body.distance);
        }
        return builder;
    }

    private static DefaultBodyBuilder toSphereBodyBuilder(ParseSphere body) {
        SphereBodyBuilder builder = new SphereBodyBuilder(body.id);
        if (body.radius != null) {
            builder.setRadius(body.radius);
        }
        if (body.angularDamping != null) {
            builder.setAngularDamping(body.angularDamping);
        }
        if (body.texture != null) {
            builder.setTexture(textures.get(body.texture));
        }
        if (body.material != null) {
            builder.setMaterial(materials.get(body.material));
        }
        if (body.color != null) {
            builder.setColor(toVector3f(body.color));
        }
        return builder;
    }

    private static DefaultBodyBuilder toDefaultBodyBuilder(DefaultBodyBuilder builder, ParseBody body) {
        if (body.mass != null) {
            builder.setMass(body.mass);
        }
        if (body.restitution != null) {
            builder.setRestitution(body.restitution);
        }
        if (body.friction != null) {
            builder.setFriction(body.friction);
        }
        if (body.position != null) {
            builder.setPos(toVector3f(body.position));
        }
        if (body.rotation != null) {
            builder.setRot(toVector3f(body.rotation));
        }
        if (body.impulse != null) {
            builder.setImpulse(toVector3f(body.impulse));
        }
        if (body.changeableParams != null) {
            for (ParseParam param : body.changeableParams) {
                builder.addChangeableParam(param.name, param.low, param.hig);
            }
        }
        return builder;
    }

    public static Level parse(File file) {
        Serializer serializer = new Persister();
        ParseSimulation sim = null;
        try {
            sim = serializer.read(ParseSimulation.class, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Level level = new Level(toVector3f(sim.gravity));
        materials = new HashMap<>();
        for (ParseMaterial parseMaterial : sim.materials) {
            materials.put(parseMaterial.name, toMaterial(parseMaterial));
        }
        textures = new HashMap<>();
        for (ParseTexture parseTexture : sim.textures) {
            textures.put(parseTexture.name, toTexture(parseTexture));
        }
        for (ParseBody parseBody : sim.bodies.bodies) {
            DefaultBodyBuilder builder;
            if (parseBody instanceof ParseBox) {
                builder = toBoxBodyBuilder((ParseBox) parseBody);
            } else if (parseBody instanceof ParseOBJModel) {
                builder = toOBJBodyBuilder((ParseOBJModel) parseBody);
            } else if (parseBody instanceof ParsePlane) {
                builder = toPlaneBodyBuilder((ParsePlane) parseBody);
            } else {
                builder = toSphereBodyBuilder((ParseSphere) parseBody);
            }
            builder = toDefaultBodyBuilder(builder, parseBody);
            level.addBody(builder);
        }
        return level;
    }

    public static void print(File file) {
        Serializer serializer = new Persister();
        ParseSimulation sim = null;
        try {
            sim = serializer.read(ParseSimulation.class, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sim);
    }
}
