package com.kopiitem.login.jaas;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 *
 * @author donny.fm
 */
public class App {

    public static void main(String[] args) {
        try {

            System.setProperty("java.security.auth.login.config", "D://jaas.config");

            LoginContext lc = new LoginContext("Sample", new MyCallbackHandler());
            lc.login();
            System.out.println(lc.getSubject().getPrincipals().toString());
        } catch (LoginException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
