package com.kopiitem.gamsuit.util;

import com.google.gson.Gson;
import com.kopiitem.gamsuit.client.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author donny.fm
 */
public class Transport {

    protected Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Gson gson;

    public Transport() {
        gson = new Gson();
    }

    public void create(String server, int port) {
        try {
            this.socket = new Socket(server, port);
            initStreams();
        } catch (IOException ex) {
            Logger.getLogger(Transport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initStreams() {
        try {
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Transport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(Player data) {
        try {
            String json = gson.toJson(data);
            out.println(json);
        } catch (Exception ex) {
            Logger.getLogger(Transport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Player read() {
        try {
            String json = in.readLine();
            if (json == null) {
                return null;
            }
            return gson.fromJson(json, Player.class);
        } catch (IOException ex) {
            Logger.getLogger(Transport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
