/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechat.common;

import java.rmi.RemoteException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Kadir Korkmaz
 */
public class Server implements ChatServer {

    private final Map<String, ChatClient> usernameToClientObject = new LinkedHashMap<>();

    public Server() {
    }

    @Override
    public String[] registerClient(ChatClient client) throws RemoteException {
        System.out.println("Login : " + client.getName());
        usernameToClientObject.put(client.getName(), client);

        Set<String> currentUserSet = usernameToClientObject.keySet();
        String[] currentUserList = currentUserSet.toArray(new String[currentUserSet.size()]);
        
        for (Map.Entry<String, ChatClient> entry : usernameToClientObject.entrySet()) {
            ChatClient c = entry.getValue();
            c.notifyLogin(client.getName());
        }

        return currentUserList;
    }

    
    @Override
    public void unregisterClient(ChatClient client) throws RemoteException {
        System.out.println("Logout : " + client.getName());
        usernameToClientObject.remove(client.getName());

        for (Map.Entry<String, ChatClient> entry : usernameToClientObject.entrySet()) {
            ChatClient c = entry.getValue();
            c.notifyLogout(client.getName());
        }

    }

    @Override
    public void sendMessage(ChatClient from, String to, String message) throws RemoteException {
        ChatClient messageReceiver = usernameToClientObject.get(to);
        if (messageReceiver != null) {
            messageReceiver.notifyMessage(from.getName(), message);
        }
    }

}
