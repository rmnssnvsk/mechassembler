package controller;

import model.Model;
import view.View;

import java.util.Observable;
import java.util.Observer;

/**
 * <p> TODO: this
 * </p>
 * <p> Класс контроллера. Контроллер обновляет {@link model.Model модель}
 * и обновляется {@link view.View представлением}.
 * </p>
 * @author Mike Sorokin
 */
public class Controller implements Observer {
    /**
     * {@link java.lang.Thread Поток}, в котором контроллер обновляет {@link model.Model модель}.
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
     * Создает новый контроллер. Устанавливает себя как контроллер для <code>view</code>.
     *
     * @param view Представление, которое обновляет этот контроллер.
     */
    public Controller(View view) {
        view.setController(this);
        getDelta();
        thread = new Thread(() -> {
            while (!isStopRequested()) {
                double dt = getDelta();
                Model.model.update(dt);
                Model.model.notifyObservers();
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
        lastFrame += currentTime;
        return delta;
    }

    /**
     * Начинает обновление {@link model.Model модели}.
     */
    public void start() {
        thread.start();
    }

    /**
     * Запрашивает остановку обновления {@link model.Model модели}.
     * Остановка произойдет после конца текущео фрейма.
     */
    public void stop() {
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

    @Override
    public void update(Observable o, Object arg) {
        //TODO: this
    }
}
