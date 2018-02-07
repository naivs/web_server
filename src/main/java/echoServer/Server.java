/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Ivan Naumov
 */
public class Server implements Runnable {

    private ServerSocket serverSocket;
    private Set<Connection> pull;
    private boolean isRunning;

    @SuppressWarnings("CallToPrintStackTrace")
    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            pull = new HashSet<>();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void stop() {
        isRunning = false;
        pull.forEach((connection) -> {
            connection.interrupt();
        });
    }

    @Override
    public void run() {
        isRunning = true;
        System.out.println("Server started");
        while (isRunning) {
            try {
                Connection connection = new Connection(serverSocket.accept());
                pull.add(connection);
                connection.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
