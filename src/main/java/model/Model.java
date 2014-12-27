package model;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import model.builder.AbstractBodyBuilder;
import model.event.GoalReachedModelEvent;
import model.event.ModelEvent;

import javax.vecmath.Vector3f;
import java.util.*;
import java.util.function.Consumer;

/**
 * Класс модели. Модель содержит в себе всю информацию о симуляции и умеет обновлять
 * ее с помощью метода {@link Model#update(float)}.
 *
 * @author Mike Sorokin
 */
public class Model extends Observable implements BodyGetter {

    /**
     * Мир, где происходит симуляция
     */
    private DynamicsWorld world;

    private Level level;
    private List<Body> bodies;
    private Map<String, Integer> bodyCache;
    private List<Consumer<BodyGetter>> forces;

    private boolean stopRequested = false;
    private RunState runState = RunState.CONF;

    /**
     * Создает и инициализирует модель.
     */
    public Model(Level level) {
        this.bodies = new ArrayList<>();
        this.bodyCache = new HashMap<>();
        this.forces = new ArrayList<>();
        this.level = level;
        BroadphaseInterface broadphaseInterface = new DbvtBroadphase();
        CollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
        CollisionDispatcher collisionDispatcher = new CollisionDispatcher(collisionConfiguration);
        ConstraintSolver constraintSolver = new SequentialImpulseConstraintSolver();
        world = new DiscreteDynamicsWorld(collisionDispatcher, broadphaseInterface, constraintSolver, collisionConfiguration);
        world.setGravity(level.getGravity());
    }

    public void start() {
        load();
        stopRequested = false;
        double lastFrame = System.currentTimeMillis() / 1000d;
        while (!stopRequested) {
            double currentTime = System.currentTimeMillis() / 1000d;
            float delta = (float) (currentTime - lastFrame);
            lastFrame = currentTime;
            update(delta);
        }
    }

    public void stop() {
        stopRequested = true;
    }

    /**
     * Обновляет состояние симуляции.
     *
     * @param t промежуток времени в секундах.
     */
    private void update(float t) {
        level.getCamera().update(t);
        if (runState == RunState.TEST) {
            forces.stream().forEach(f -> f.accept(this));
            world.stepSimulation(t);
        }
        List<ModelEvent> events = new ArrayList<>();
        if (level.getGoal().isGoalReached(bodies, t)) {
            events.add(new GoalReachedModelEvent(this));
        }
        setChanged();
        notifyObservers(events);
    }

    private void load() {
        int i = 0;
        for (AbstractBodyBuilder b : level.getBodies()) {
            Body body = b.build();
            bodies.add(body);
            bodyCache.put(body.id, i++);
        }
        bodies.stream().forEach(b -> world.addRigidBody(b.getRigidBody()));
    }

    public void reload() {
        bodies.stream().forEach(b -> world.removeRigidBody(b.getRigidBody()));
        bodies.clear();
        bodyCache.clear();
        load();
    }

    /**
     * Возвращает текущий вектор ускорения свободгого падения.
     *
     * @return текущий вектор ускорения свободгого падения
     */
    public Vector3f getGravity() {
        return world.getGravity(new Vector3f());
    }

    /**
     * Устанавливает вектор свободного падения.
     *
     * @param gravity новый вектор ускорения свободного падения
     */
    public void setGravity(Vector3f gravity) {
        world.setGravity(gravity);
    }

    public DynamicsWorld getWorld() {
        return world;
    }

    public RunState getRunState() {
        return runState;
    }

    public void setRunState(RunState state) {
        if (state == RunState.CONF) {
            reload();
        }
        this.runState = state;
    }

    public List<Body> getBodies() {
        return bodies;
    }

    public void reloadBodyById(String id) {
        int i = bodyCache.get(id);
        world.removeRigidBody(bodies.get(i).getRigidBody());
        bodies.set(i, ((AbstractBodyBuilder) bodies.get(i).getRigidBody().getUserPointer()).build());
        world.addRigidBody(bodies.get(i).getRigidBody());
    }

    @Override
    public Body getBodyById(String id) {
        return bodies.get(bodyCache.get(id));
    }

    public void addForce(Consumer<BodyGetter> force) {
        forces.add(force);
    }

    public void delete() {
        bodies.stream().forEach(Body::delete);
    }
}
