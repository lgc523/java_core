package cn.spider.app.thread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateThreadTest {

    @Test
    public void testCreateThread() {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(() -> System.out.println("lambda create thread..."));
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            System.out.println("executors ...");
        });
        service.shutdown();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("extends thread...");
    }
}

class MyRun implements Runnable {

    @Override
    public void run() {
        System.out.println("impl runnable...");
    }
}

class MyCall implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("impl callable...");
        return "impl callable...";
    }
}