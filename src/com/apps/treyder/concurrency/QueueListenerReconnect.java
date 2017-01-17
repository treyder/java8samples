package com.apps.treyder.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by adminuser on 1/17/17.
 */
public class QueueListenerReconnect {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor((Runnable runnable) -> new Thread(runnable, "listener-thread"));

    private static class ListenerRunable implements Runnable{

        private volatile AtomicBoolean running;

        private final Object lock = new Object();

        ListenerRunable() {
            running = new AtomicBoolean(false);
        }

        public void stop() {
            synchronized (lock) {
                running.set(false);
            }
        }

        @Override
        public void run() {

            System.out.println("queue listener runnable started");

            synchronized (lock) {
                running.set(true);
            }

            AtomicInteger count = new AtomicInteger(0);

            while (running.get()) {

                System.out.println("waiting on queue listener");
                try {
                    Thread.sleep(5000);

                    if (count.incrementAndGet() > 2) {
                        throw new Exception("Connection error");
                    }

                } catch (Exception e) {
                    System.err.println("exception caught: " + e.getMessage());
                    synchronized (lock) {
                        running.set(false);
                    }
                    System.out.println("queue listener runnable will now exit abnormally");
                    throw new RuntimeException(e);
                }
            }

            System.out.println("queue listener runnable: graceful shutdown");

        }
    }

    private ListenerRunable listenerRunable;

    private volatile AtomicBoolean stopFlag;
    private final Object lock = new Object();

    public QueueListenerReconnect() {
        stopFlag = new AtomicBoolean(false);
    }

    public void stop() {
        synchronized (lock) {
            this.stopFlag.set(true);
        }
        listenerRunable.stop();
        executorService.shutdown();
    }

    public void simulate() {

        System.out.println("simulate(): started");


        while (!stopFlag.get()) {
            listenerRunable = new ListenerRunable();
            Future future = executorService.submit(listenerRunable);
            try {
                future.get();
                System.out.println("simulate(): listener exited OK");
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("simulate(): exception caught: " + e.getMessage());
                System.out.println("simulate(): listener exited abnormally");
            }
        }

        System.out.println("simulate(): ended");
    }


    public static void main(String [] args) {
        QueueListenerReconnect queueListenerReconnect = new QueueListenerReconnect();
        Runnable runnable = queueListenerReconnect::simulate;
        Thread thread = new Thread(runnable, "main-thread-for-simulate");
        thread.start();

        System.out.println("Now waiting 30 seconds to shutdown");
        try {
            Thread.sleep(32 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waiting finished. Shutting down");
        queueListenerReconnect.stop();

        System.out.println("Flag stopped set, exiting main()");
    }

}
