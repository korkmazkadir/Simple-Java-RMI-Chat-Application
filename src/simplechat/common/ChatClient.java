/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechat.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Kadir Korkmaz
 */
public interface ChatClient extends Remote {

    public String getName() throws RemoteException;

    public void notifyLogin(String username) throws RemoteException;

    public void notifyLogout(String username) throws RemoteException;

    public void notifyMessage(String fromUsername, String message) throws RemoteException;
    
}
