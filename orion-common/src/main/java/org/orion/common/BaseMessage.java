package org.orion.common;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-22
 */
@Data
public class BaseMessage {
    @NotEmpty
    private String lessonUID;
    private int businessType;

    public BaseMessage() {
    }

    public BaseMessage(@NotEmpty String lessonUID, int businessType) {
        this.lessonUID = lessonUID;
        this.businessType = businessType;
    }
}
