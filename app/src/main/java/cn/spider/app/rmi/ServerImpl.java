package cn.spider.app.rmi;

import java.rmi.RemoteException;

public class ServerImpl  implements RMIHandler {
//public class ServerImpl extends UnicastRemoteObject implements RMIHandler {

//    protected ServerImpl() throws RemoteException {
//    }

    @Override
    public String rmi(String val) throws RemoteException {
        String upperCase = val.toUpperCase();
        System.out.println(upperCase);
        return upperCase;
    }
}
