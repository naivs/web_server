/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Ivan Naumov
 */
public class ServerTest {

    private static Thread server;
    private Socket socket;
    private PrintWriter bw;
    private BufferedReader br;
    private final static int PORT = 8080;

    @BeforeClass
    public static void beforeClass() {
        server = new Thread(new Server(PORT));
    }

    @Before
    @SuppressWarnings("CallToPrintStackTrace")
    public void setUp() {
        server.start();

        try {
            socket = new Socket("localhost", PORT);
            while (!socket.isBound()) {
                System.out.println("Waiting for connection");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {

                }
            }
            bw = new PrintWriter(socket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException ex) {
            socketClose();
            ex.printStackTrace();
            fail();
        } catch (IOException ex) {
            socketClose();
            ex.printStackTrace();
            fail();
        }
    }

    @After
    public void tearDown() {
        socketClose();
        server.interrupt();
    }

    private void socketClose() {
        try {
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testEcho() {
        String expectMsg = "hello mars";
        String msg = "";

        try {
            bw.println(expectMsg);
            msg = br.readLine();
            System.out.println(msg);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        assertEquals(expectMsg, msg);
    }
}
