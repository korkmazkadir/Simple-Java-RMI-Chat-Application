/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechat.common;

import java.rmi.RemoteException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Kadir Korkmaz
 */
public class Client implements ChatClient {

    private final String username;

    private final Set<String> onlineUsers = new LinkedHashSet<>();

    public Client(String username) {
        this.username = username;
    }

    public Set<String> getOnlineUsers() {
        return onlineUsers;
    }

    public void addCurrentUsers(String[] currentUsers) {
        for (String currentUser : currentUsers) {
            onlineUsers.add(currentUser);
        }
    }

    @Override
    public String getName() throws RemoteException {
        return username;
    }

    @Override
    public void notifyLogin(String username) throws RemoteException {
        onlineUsers.add(username);
    }

    @Override
    public void notifyLogout(String username) throws RemoteException {
        onlineUsers.remove(username);
    }

    @Override
    public void notifyMessage(String fromUsername, String message) {
        System.out.println("\n==================================================================");
        System.out.println("NEW MESSAGE : from -> " + fromUsername + " message -> " + message);
        System.out.println("==================================================================\n");
    }

}
