package org.orion.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-22
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class ChannelMessage extends BaseMessage {
    private String streamId;
    private String streamMd5;
    private String role;
    private Integer tencentRoomId;
    private Integer width;
    private Integer height;
    private Integer x;
    private Integer y;
    private String channel;
}
