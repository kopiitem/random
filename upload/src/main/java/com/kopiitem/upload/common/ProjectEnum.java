package com.kopiitem.upload.common;

/**
 *
 * @author donny.fm
 */
public enum ProjectEnum {

    PTN_T1("partner"), ENT("enterprise-new"), PTN_T2("partner-t2");

    private String desc;

    private ProjectEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
