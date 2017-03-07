package com.kopiitem.gamsuit.util;

import com.kopiitem.gamsuit.client.Player;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author donny.fm
 */
public class Transport {

    protected Socket socket;
    private String server;
    private int port;

    public Transport() {
    }

    public void create(String server, int port) {
        try {
            this.socket = new Socket(server, port);
        } catch (IOException ex) {
            Logger.getLogger(Transport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(Player data) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(data);
        } catch (IOException ex) {
            Logger.getLogger(Transport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Player read() {
        try {
            return (Player) new ObjectInputStream(socket.getInputStream()).readObject();
        } catch (IOException ex) {
            Logger.getLogger(Transport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
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
