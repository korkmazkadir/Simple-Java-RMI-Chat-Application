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
public interface ChatServer extends Remote {

    public String[] registerClient(ChatClient client) throws RemoteException;

    public void unregisterClient(ChatClient client) throws RemoteException;

    public void sendMessage(ChatClient from, String to, String message) throws RemoteException;
    
}
