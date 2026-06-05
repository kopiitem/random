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
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Transport() {
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
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.out.flush();
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Transport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(Player data) {
        try {
            out.writeObject(data);
            out.flush();
            out.reset();
        } catch (IOException ex) {
            Logger.getLogger(Transport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Player read() {
        try {
            return (Player) in.readObject();
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
