package model;

import model.builder.AbstractBodyBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 12/15/14.
 *
 * @author Mike Sorokin
 */
public class Level {
    private List<AbstractBodyBuilder> bodies;

    public Level() {
        bodies = new ArrayList<>();
    }

    public void addBody(AbstractBodyBuilder body) {
        bodies.add(body);
    }

    public void removeBody(AbstractBodyBuilder body) {
        bodies.remove(body);
    }

    public List<AbstractBodyBuilder> getBodies() {
        return bodies;
    }
}
