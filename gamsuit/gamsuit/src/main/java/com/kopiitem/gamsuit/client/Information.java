package com.kopiitem.gamsuit.client;

import com.kopiitem.gamsuit.util.BidEnum;
import java.io.Serializable;

/**
 *
 * @author donny.fm
 */
public class Information implements Serializable {

    private String name;
    private int score;
    private BidEnum bid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public BidEnum getBid() {
        return bid;
    }

    public void setBid(BidEnum bid) {
        this.bid = bid;
    }

}
