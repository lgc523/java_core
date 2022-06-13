package cn.spider.app.rmi;

import java.rmi.registry.LocateRegistry;
import java.util.concurrent.CountDownLatch;

public class RegisterServer {
    public static void main(String[] args) throws InterruptedException {
        try {
            LocateRegistry.createRegistry(8000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("registry over...");
        CountDownLatch cdl = new CountDownLatch(1);
        cdl.await();
    }

}
