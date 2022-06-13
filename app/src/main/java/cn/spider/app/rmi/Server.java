package cn.spider.app.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) throws Exception {
        RMIHandler rmiHandler = new ServerImpl();
//        Registry registry = LocateRegistry.createRegistry(1099);
//        registry.bind("rmi", rmiHandler);
        //
        RMIHandler stub = (RMIHandler) UnicastRemoteObject.exportObject(rmiHandler,4396);
        Registry reg = LocateRegistry.getRegistry("127.0.0.1",8000);
        System.out.println("server bind...");
        reg.rebind("rmi",stub);
        System.out.println("server bind over...");
    }
}
