package model;

import com.bulletphysics.dynamics.RigidBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс модели. Модель содержит в себе всю информацию о симуляции и умеет обновлять
 * ее с помощью метода {@link model.Model#update(double)}.
 *
 * @author Mike Sorokin
 */
public class Model {
    private List<RigidBody> bodies;

    public Model() {
        bodies = new ArrayList<>();
    }

    /**
     * Обновляет состояние симуляции.
     * @param t промежуток времени в секундах.
     */
    public void update(double t) {
        // TODO: взимодействие тел
    }

    /**
     * Добавляет тело в симуляцию.
     * @param body тело
     */
    public void addBody(RigidBody body) {
        bodies.add(body);
    }

    /**
     * Удаляет тело из симуляции.
     * @param body тело
     */
    public void removeBody(RigidBody body) {
        bodies.remove(body);
    }

    /**
     * Возвращает список тел, учавствующих в симуляции.
     * @return список тел, учавствующих в симуляции
     */
    public List<RigidBody> getBodies() {
        return bodies;
    }

    /**
     * Инициализирует модель. Обязательно вызвать перед использованием этой модели.
     */
    public void init() {
        //TODO: инициализация модели (если надо).
    }

    /**
     * Удаляет объекты, используемые моделью, причем после этого оно будет непригодно к исползованию.
     * Обязательно вызывать перед завершением работы с этой моделью.
     */
    public void destroy() {
        //TODO: удаление объектов (если надо).
    }
}
