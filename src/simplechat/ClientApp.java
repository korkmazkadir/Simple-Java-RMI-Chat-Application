/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;
import simplechat.common.ChatClient;
import simplechat.common.ChatServer;
import simplechat.common.Client;

/**
 *
 * @author Kadir Korkmaz
 */
public class ClientApp {

    private static ChatServer chatServer;
    private static Client client;
    private static ChatClient clientStub;

    public static void main(String[] args) throws RemoteException, NotBoundException, IOException {

        String username = "Kadir";
        String serverAddress = "localhost";

        Registry registry = LocateRegistry.getRegistry(serverAddress, 2020);

        if (registry == null) {
            System.err.println("Error : Registry is null :(. Did you run a server");
            return;
        }

        client = new Client(username);
        clientStub = (ChatClient) UnicastRemoteObject.exportObject(client, 0);

        chatServer = (ChatServer) registry.lookup("ChatServerObject");
        String[] currentUsers = chatServer.registerClient(clientStub);
        client.addCurrentUsers(currentUsers);
        
        System.out.println("Connected to server...");

        handleUserRequests();

    }

    private static void showMenu() {
        System.out.println("1 - Show online users");
        System.out.println("2 - Send Message");
        System.out.println("3 - Logout");
    }

    private static int getUserSelection() {
        InputStreamReader read = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(read);
        int userSelection;
        
        try {
            userSelection = Integer.parseInt(in.readLine());
        } catch (Exception ex) {
            System.err.println("input is not valid...");
            return -1;
        }

        if (userSelection < 0 || userSelection > 3) {
            System.err.println("Enter a number between 1 and 3 t oselect the operation");
            return -1;
        }

        return userSelection;
    }

    private static void sendMessage() throws IOException {
        System.out.println("write username, write space and write message to send");
        InputStreamReader read = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(read);
        
        String line = in.readLine();
        String[] tokens = line.split(" ");
        
        if(tokens.length < 2){
            System.err.println("Wrong format. Try again");
            return;
        }
        
        String receiverName = tokens[0];
        String message = line.replaceFirst(receiverName, "");;
        
        Set<String> onlineUsers = client.getOnlineUsers();
        if(onlineUsers.contains(receiverName) == false){
            System.err.println("User " + receiverName +" is not online. Try again later...");
            return;
        }
        
        
        
        chatServer.sendMessage(clientStub, receiverName, message);
        System.out.println("Message send");
        
    }

    private static void handleSelection(int userSelection) throws RemoteException, IOException {

        switch (userSelection) {
            case 1: {
                Set<String> onlineUsers = client.getOnlineUsers();
                System.out.println("-------- Online Users --------");
                for (String onlineUser : onlineUsers) {
                    System.out.println(onlineUser);
                }
                System.out.println("------------------------------\n");
            }
            break;

            case 2: {
                System.out.println("-------- Send Message --------");
                sendMessage();
                System.out.println("------------------------------\n");
            }
            break;

            case 3: {
                System.out.println("Exiting...\n");
                chatServer.unregisterClient(clientStub);
                System.exit(0);
            }
            break;

        }

    }

    private static void handleUserRequests() throws RemoteException, IOException {
        while (true) {
            showMenu();
            int userSelection = getUserSelection();
            if (userSelection < 0) {
                continue;
            }
            handleSelection(userSelection);
        }
    }

}
