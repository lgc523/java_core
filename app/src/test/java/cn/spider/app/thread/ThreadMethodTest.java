package cn.spider.app.thread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ThreadMethodTest {
    @Test
    public void testThreadSleep_Yield_Join() throws InterruptedException {
        testSleep();
        testYield();
        testJoin();
        TimeUnit.SECONDS.sleep(5);
    }

    static void testSleep() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                //当前线程正在执行时，让出 CPU，等待调度
                if (i % 10 == 0) Thread.yield();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("----------B" + i);
            }
        }).start();
    }

    static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {

            try {
                //当前线程等待 join 线程执行完了在执行
                t1.join();
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }

}
