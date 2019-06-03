package org.orion.common;

import lombok.Data;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-23
 */
@Data
public class Task {
    /**
     * 课程id
     */
    private String lessonUID;

    /**
     * 通道名
     * @see com.zhangmen.enumeration.ChannelEnum
     */
    private String channel;

    /**
     * 网易通道id
     */
    private String neteaseChannelUID;

    /**
     * 本次任务的负载
     */
    private int load;

    /**
     * 本次任务是否录制音频
     */
    private boolean audio;

    /**
     * 本次任务是否录制视频
     */
    private boolean video;

    /**
     * 任务耗时
     */
    private long time;
}
