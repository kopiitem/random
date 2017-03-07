package com.kopiitem.login.jaas;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 *
 * @author donny.fm
 */
public class SampleLoginModule implements LoginModule {

    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map<String, ?> sharedState;
    private Map<String, ?> options;

    private boolean debug = false;

    private String userName;
    private char[] password;

    private boolean succeeded = false;
    private MyUserPrinciple userPrinciple;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
        debug = "true".equalsIgnoreCase((String) options.get("debug"));
    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler == null) {
            throw new LoginException("Error: no CallbackHandler available "
                    + "to garner authentication information from the user");
        }

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("user name: ");
        callbacks[1] = new PasswordCallback("password: ", false);

        try {
            callbackHandler.handle(callbacks);
            userName = ((NameCallback) callbacks[0]).getName();
            char[] tmpPassword = ((PasswordCallback) callbacks[1]).getPassword();
            if (tmpPassword == null) {
                // treat a NULL password as an empty password
                tmpPassword = new char[0];
            }
            password = new char[tmpPassword.length];
            System.arraycopy(tmpPassword, 0,
                    password, 0, tmpPassword.length);
            ((PasswordCallback) callbacks[1]).clearPassword();

            if (userName.equalsIgnoreCase("Donny") && Arrays.toString(password).equalsIgnoreCase(Arrays.toString("password".toCharArray()))) {
                succeeded = true;
            }

            if (debug) {
                System.out.println("\t\t[SampleLoginModule] "
                        + "added SamplePrincipal to Subject " + userName + " - " + password.toString());
            }

            return true;
        } catch (IOException ex) {
            Logger.getLogger(SampleLoginModule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedCallbackException ex) {
            Logger.getLogger(SampleLoginModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean commit() throws LoginException {
        if (succeeded == true) {
            userPrinciple = new MyUserPrinciple(userName);
            if (!subject.getPrincipals().contains(userPrinciple)) {
                subject.getPrincipals().add(userPrinciple);
            }
            userName = null;
            password = null;
            return true;
        }
        return false;
    }

    @Override
    public boolean abort() throws LoginException {

        return logout();
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrinciple);
        succeeded = false;
        userName = null;
        password = null;

        userPrinciple = null;

        return true;
    }

}
