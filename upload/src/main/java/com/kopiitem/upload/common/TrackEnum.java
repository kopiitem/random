package com.kopiitem.upload.common;

/**
 *
 * @author donny.fm
 */
public enum TrackEnum {

    SITT1(""), DEVT1("-devsvn"), SITT2(""), DEVT2("-dev-t2svn");

    private String desc;

    private TrackEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
