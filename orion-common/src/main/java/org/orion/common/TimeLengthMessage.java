package org.orion.common;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-23
 */
public class TimeLengthMessage {

    /**
     * channelId : 6265489843204131361
     * createtime : 1458798033925
     * duration : 25
     * eventType : 5
     * live : 0
     * members : [{"accid":"lisi","duration":13},{"accid":"zhangsan","caller":true,"duration":12}]
     * status : SUCCESS
     * type : AUDIO
     */

    private String channelId;
    private String createtime;
    private String duration;
    private String eventType;
    private String live;
    private String members;
    private String status;
    private String type;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
