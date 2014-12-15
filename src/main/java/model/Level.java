package model;

import model.builder.BodyBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 12/15/14.
 *
 * @author Mike Sorokin
 */
public class Level {
    private List<BodyBuilder> bodies;

    public Level() {
        bodies = new ArrayList<>();
    }

    public void addBody(BodyBuilder body) {
        bodies.add(body);
    }

    public void removeBody(BodyBuilder body) {
        bodies.remove(body);
    }

    public List<BodyBuilder> getBodies() {
        return bodies;
    }
}
