package com.kopiitem.login.jaas;

import java.io.Serializable;
import java.security.Principal;
import java.util.Objects;

/**
 *
 * @author donny.fm
 */
public class MyUserPrinciple implements Principal, Serializable {

    private String name;

    public MyUserPrinciple() {
    }

    public MyUserPrinciple(String name) {
        if (name == null) {
            throw new NullPointerException("illegal null input");
        }

        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MyUserPrinciple other = (MyUserPrinciple) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MyUserPrinciple{" + "name=" + name + '}';
    }

}
