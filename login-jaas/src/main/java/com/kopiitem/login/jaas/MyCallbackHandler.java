package com.kopiitem.login.jaas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 *
 * @author donny.fm
 */
public class MyCallbackHandler implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                NameCallback nc = (NameCallback) callback;
                System.out.print(nc.getPrompt());
                System.out.flush();
                nc.setName(new BufferedReader(new InputStreamReader(System.in)).readLine());
            }
            if (callback instanceof PasswordCallback) {
                PasswordCallback pc = (PasswordCallback) callback;
                System.out.print(pc.getPrompt());
                System.out.flush();
                pc.setPassword(new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray());
            }
        }

    }

}
