package org.orion.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-22
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class ChannelChangedMessage extends BaseMessage {
    @NotEmpty
    private String switchChannel;
}
