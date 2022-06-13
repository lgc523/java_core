package cn.spider.app.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIHandler extends Remote {
    String rmi(String val) throws RemoteException;
}
