package main;

import controller.Controller;
import model.Model;
import view.View;

/**
 * <p>
 *     Главный класс. Создает модель, представление, контроллер, инициализирует и использует их.
 * </p><p>
 *     Если исрользуются нативные библиотеки, то необходимо указать параметр запуска
 *     <code>-Djava.library.path=lib/native</code>
 * </p>
 *
 *
 * @author Mike Sorokin
 */

public class Main {

    /**
     * Главный метод. Если исрользуются нативные библиотеки, то необходимо указать параметр запуска
     * <code>-Djava.library.path=lib/native</code>
     * @param args Аргументы, с которыми была вызвана программа.
     */
    public static void main(String[] args) {
        Model model = new Model();
        // TODO: Загрузка/созлание модели.
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.init();
        controller.startSimulation();
        /*
        Когда приложение завершает работу контроллер остановится сам, т.е. здесь должен быть код типа
        while (controller.isRunning())
            Thread.yield();
        Т.к. сейчас контроллер свою работу самостоятельно не завершает, заглушка.
         */
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        controller.stopSimulation();
        controller.destroy();
    }
}
