package model;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.CollisionObjectType;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import view.Camera;

import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Класс модели. Модель содержит в себе всю информацию о симуляции и умеет обновлять
 * ее с помощью метода {@link model.Model#update(float)}.
 *
 * @author Mike Sorokin
 */
public class Model extends Observable {
    /**
     * Вектор ускорения свободного падения по умолчанию.
     */
    public static final Vector3f DEFAULT_GRAVITY = new Vector3f(0, -10, 0);

    /**
     * Мир, где происходит симуляция
     */
    private DynamicsWorld world;

    /**
     * Список тел, учавствующих в симуляции
     */
    private List<Body> bodies;

    private Camera camera;
    private boolean stopRequested = false;

    /**
     * Создает и инициализирует модель. В качестве векора ускорения свободгого падения
     * используется {@link model.Model#DEFAULT_GRAVITY}
     */
    public Model(Camera camera) {
        this(camera, DEFAULT_GRAVITY);
    }

    /**
     * Создает и инициализирует модель.
     * @param gravity вектор ускорения свободного падения
     */
    public Model(Camera camera, Vector3f gravity) {
        this.camera = camera;
        bodies = new ArrayList<>();
        BroadphaseInterface broadphaseInterface = new DbvtBroadphase();
        CollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
        CollisionDispatcher collisionDispatcher = new CollisionDispatcher(collisionConfiguration);
        ConstraintSolver constraintSolver = new SequentialImpulseConstraintSolver();
        world = new DiscreteDynamicsWorld(collisionDispatcher, broadphaseInterface, constraintSolver, collisionConfiguration);
        world.setGravity(gravity);
    }

    public void start() {
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
     * @param t промежуток времени в секундах.
     */
    private void update(float t) {
        camera.update(t);
        world.stepSimulation(t);
        setChanged();
        notifyObservers(bodies);
    }

    /**
     * Добавляет тело в симуляцию.
     * @param body тело
     */
    public void addBody(Body body) {
        bodies.add(body);
        world.addRigidBody(body.getRigidBody());
    }

    /**
     * Удаляет тело из симуляции.
     * @param body тело
     */
    public void removeBody(Body body) {
        bodies.remove(body);
        world.removeRigidBody(body.getRigidBody());
        body.getRigidBody().destroy();
    }

    /**
     * Возвращает список тел, учавствующих в симуляции.
     * @return список тел, учавствующих в симуляции
     */
    public List<Body> getBodies() {
        return bodies;
    }

    /**
     * Устанавливает вектор свободного падения.
     * @param gravity новый вектор ускорения свободного падения
     */
    public void setGravity(Vector3f gravity) {
        world.setGravity(gravity);
    }

    /**
     * Возвращает текущий вектор ускорения свободгого падения.
     * @return текущий вектор ускорения свободгого падения
     */
    public Vector3f getGravity() {
        return world.getGravity(new Vector3f());
    }

    /**
     * Удаляет объекты, используемые моделью, причем после этого оно будет непригодно к использованию.
     * Обязательно вызывать перед завершением работы с этой моделью.
     */
    public void delete() {
        bodies.stream().forEach(Body::delete);
    }
}
