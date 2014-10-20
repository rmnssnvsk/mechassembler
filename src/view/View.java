package view;

import com.bulletphysics.dynamics.RigidBody;
import controller.Controller;
import model.Model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Класс представления. Представление умеет визуализировать симуляцию.
 *
 * @author Mike Sorokin
 */
public class View {

    /**
     * Инициализирует представление. Обязательно вызвать перед использованием этого представления.
     */
    public void init() {
        //TODO: инициализация окна, контекстов openGL, openAL (если будем использовать звук), текстур, шейдеров, и еще чего-нибудь.
    }

    /**
     * Удаляет объекты, используемые представлением, причем после этого оно будет непригодно к исползованию.
     * Обязательно вызывать перед завершением работы с этим представлением.
     */
    public void destroy() {
        //TODO: удаление текстур, шейдеров, программ (которые для openGL), звуков, displayList' ов и т.д.
        //Хоть мы и пишем на джаве, openGL и openAL - Сишные, и все объекты надо поудалять.
    }

    /**
     * Визуализирует симуляцию.
     * @param bodies тела в симуляции (возвращаются методом {@link model.Model#getBodies()})
     */
    public void show(List<RigidBody> bodies) {
        bodies.stream().forEach(body -> {
            //TODO: визуализация одного тела
        });
    }
}
