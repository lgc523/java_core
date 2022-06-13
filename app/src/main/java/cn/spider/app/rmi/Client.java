package cn.spider.app.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 8000);
//        Registry registry = LocateRegistry.getRegistry();
        RMIHandler rmi = (RMIHandler) registry.lookup("rmi");
        System.out.println("found remote server clint");
        rmi.rmi("hello");
    }
}
