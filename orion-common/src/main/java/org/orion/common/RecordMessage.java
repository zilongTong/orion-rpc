package org.orion.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Generated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-20
 */
@Generated("GsonFormat")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class RecordMessage extends BaseMessage {
    @NotEmpty
    private String lessonType;
    @Pattern(regexp = "^agora|zego|tencent|tencent1|tencent2|zmrtc|zmlearn|talkcloud|neteasecloud$")
    private String firstChannel;
    private long lessonStartTime;
    private int shouldEndTime;
    private int shouldStartTime;
    private int teacherId;
    private List<Integer> studentId;

    public RecordMessage() {
    }

    public RecordMessage(@NotEmpty String lessonUID, int businessType, @NotEmpty String lessonType, String firstChannel) {
        super(lessonUID, businessType);
        this.lessonType = lessonType;
        this.firstChannel = firstChannel;
    }
}
