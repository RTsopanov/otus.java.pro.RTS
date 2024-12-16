package otusPro.Multithreading;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Worker> workers;
    private final LinkedList<Runnable> taskQueue;
    private boolean isShutdown = false;

    public ThreadPool(int numberOfThreads) {
        taskQueue = new LinkedList<>();
        workers = new LinkedList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            worker.start();
        }
    }

    public synchronized void execute(Runnable task) {
        if (isShutdown) {
            throw new IllegalStateException("ThreadPool is shut down. No new tasks can be accepted.");
        }
        taskQueue.addLast(task);
        notify(); // Уведомляем потоки, что есть новая задача
    }

    public synchronized void shutdown() {
        isShutdown = true;
        for (Worker worker : workers) {
            worker.interrupt(); // Прерываем поток
        }
    }

    private class Worker extends Thread {
        public void run() {
            while (true) {
                Runnable task;
                synchronized (ThreadPool.this) {
                    while (taskQueue.isEmpty()) {
                        try {
                            ThreadPool.this.wait(); // Ждем, пока не появится новая задача
                        } catch (InterruptedException e) {
                            return; // Если поток прерван, выходим из метода
                        }
                    }
                    task = taskQueue.removeFirst();
                }
                // Выполняем задачу
                task.run();
            }
        }
    }
}
