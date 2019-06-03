package org.orion.common;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-23
 */
public class DownloadMessage {

    /**
     * eventType : 6
     * fileinfo : [{"caller":true,"channelid":"6290737000999815988","filename":"xxxxxx.type","md5":"a9b248e29669d588dd0b10259dedea1a","mix":false,"size":"2167","type":"gz","vid":"1062591","url":"http://xxxxxxxxxxxxxxxxxxxx.type","user":"zhangsan"}]
     */

    private String eventType;
    private String fileinfo;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getFileinfo() {
        return fileinfo;
    }

    public void setFileinfo(String fileinfo) {
        this.fileinfo = fileinfo;
    }
}
