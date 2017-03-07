package com.kopiitem.gamsuit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
//                    PrintWriter out
//                            = new PrintWriter(socket.getOutputStream(), true);
//                    out.println("Message From Server, " + new Date().toString());
//
                    BufferedReader input
                            = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String answer = input.readLine();
                    JOptionPane.showMessageDialog(null, answer);

                } finally {
//                    socket.close();
                }
            }
        } finally {
            listener.close();
        }
    }
}
