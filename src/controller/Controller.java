package controller;

import com.bulletphysics.dynamics.RigidBody;
import model.Model;
import view.View;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Класс контроллера. Контроллер обновляет {@link model.Model модель}
 * и визуализирует ее с помощью {@link view.View представления}
 *
 * @author Mike Sorokin
 */
public class Controller {
    /**
     * Поток, в котором контроллер обновляет {@link model.Model модель}.
     */
    private Thread thread;

    /**
     * Если был вызван метод <code>stop()</code>, то эта переменная принимает значение <code>true</code>,
     * иначе <code>false</code>. Т. е. значение этой переменной истинно тогда и только тогда,
     * когда было запрошено завершение.
     */
    private boolean stopRequested = false;

    /**
     * Время рассчета последнего фрейма в секундах.
     */
    private double lastFrame = 0;

    /**
     * Модель, которую использует и обновляет этот контроллер.
     */
    private Model model;

    /**
     * Представление, когорое использует этот контроллер.
     */
    private View view;

    /**
     * Создает новый контроллер, который использует и обновляет модель <code>model</code>,
     * и представление <code>view</code>.
     * @param model модель, которую использует и обновляет этот контроллер
     * @param view представление, когорое использует этот контроллер
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.thread = new Thread(() -> {
            while (!isStopRequested()) {
                model.update(getDelta());
                updateView();
            }
        });
    }

    /**
     * Возвращает значение переменной <code>{@link controller.Controller#stopRequested stopRequested}</code>.
     * @return {@link controller.Controller#stopRequested stopRequested}.
     */
    private boolean isStopRequested() {
        return stopRequested;
    }

    /**
     * Возвращает текущее время в секундах.
     * @return Текущее время в секундах.
     */
    private double getTime() {
        return System.currentTimeMillis() / 1000d;
    }

    /**
     * Возвращает время, которое прошло с момента последнего вызова этого метода в секундах.
     * Первый вызов этого метода возвращает время, которое прошло с полночи 1 Января 1970 года в секундах.
     * @return Время, которое прошло с момента последнего вызова этого метода в секундах.
     */
    private double getDelta() {
        double currentTime = getTime();
        double delta = currentTime - lastFrame;
        lastFrame += delta;
        return delta;
    }

    /**
     * Начинает обновление {@link model.Model модели}.
     */
    public void startSimulation() {
        this.stopRequested = false;
        thread.start();
    }

    /**
     * Запрашивает остановку обновления {@link model.Model модели}.
     * Остановка произойдет после конца текущео фрейма.
     */
    public void stopSimulation() {
        this.stopRequested = true;
    }

    /**
     * Возвращает <code>true</code>, если обновление {@link model.Model модели} запущено (т. е. был сделан
     * вызов метода <code>start()</code>), и после это не было остановлено (т. е. не был
     * сделан вызов метода <code>stop()</code>).
     * @return Идет ли сейчас обновление {@link model.Model модели}.
     */
    public boolean isRunning() {
        return thread.isAlive();
    }

    /**
     * Добавляет тело в симуляцию.
     * @param body тело
     */
    public void addBody(RigidBody body) {
        model.addBody(body);
    }

    /**
     * Удаляет тело из симуляции.
     * @param body тело
     */
    public void removeBody(RigidBody body) {
        model.removeBody(body);
    }

    /**
     * Возвращает список тел, учавствующих в симуляции.
     * @return список тел, учавствующих в симуляции
     */
    public List<RigidBody> getBodies() {
        return model.getBodies();
    }

    /**
     * Визуализирует симуляцию.
     */
    public void updateView() {
        view.show(model.getBodies());
    }

    /**
     * Инициализирует модель и представление. Обязательно вызвать перед использованием этого контроллера.
     */
    public void init() {
        model.init();
        view.init();
    }

    /**
     * Удаляет объекты, используемые моделью и представлением, причем после этого контроллер будет непригоден к исползованию.
     * Обязательно вызывать перед завершением работы с этим контроллером.
     */
    public void destroy() {
        model.destroy();
        view.destroy();
    }
}
