/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Ivan Naumov
 */
public class Connection extends Thread {

    private final Socket socket;
    private PrintWriter bw;
    private BufferedReader br;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void run() {
        try {
            bw = new PrintWriter(socket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        while (true) {
            try {
                String message = br.readLine();
                if (message != null) {
                    if (message.equals("Bye.")) {
                        return;
                    }
                    bw.println(message);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
