package com.kopiitem.gamsuit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author donny.fm
 */
public class DateClient {

    public static void main(String[] args) throws IOException {
        String serverAddress = JOptionPane.showInputDialog(
                "Enter IP Address of a machine that is\n"
                + "running the date service on port 9090:");
        Socket socket = new Socket(serverAddress, 9090);
        BufferedReader input
                = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String answer = input.readLine();
        JOptionPane.showMessageDialog(null, answer);

        try {
            PrintWriter out
                    = new PrintWriter(socket.getOutputStream(), true);
            out.println("Message From Client, " + new Date().toString());
        } finally {
            socket.close();
        }

        System.exit(0);
    }
}
