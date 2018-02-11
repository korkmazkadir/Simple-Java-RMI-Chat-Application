/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechat;

import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import simplechat.common.ChatServer;
import simplechat.common.Server;

/**
 *
 * @author Kadir Korkmaz
 */
public class ServerApp {

    public static void main(String[] args) throws IOException, RemoteException, AccessException, AlreadyBoundException {
        
        System.out.println("Creating RMI registry");
        Runtime.getRuntime().exec("rmiregistry 2020");
        LocateRegistry.createRegistry(2020);

        System.out.println("Creating Server Object");
        Server chatServer = new Server();
        ChatServer serverStub = (ChatServer) UnicastRemoteObject.exportObject(chatServer, 0);

        System.out.println("Registring Server object to registry");
        // Bind the remote object's stub in the registry
        Registry registry = LocateRegistry.getRegistry(2020);
        registry.bind("ChatServerObject", serverStub);

        System.out.println("Server ready...");

    }

}
