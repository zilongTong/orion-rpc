package org.orion.common;

import lombok.Data;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-29
 */
@Data
public class LessonInfoMessage {
    private Integer studentId;
    private String studentName;
    private String teacherName;
    private String studentPhoneNo;
    private String startTime;
    private String endTime;
    private String lessonName;
    private Integer courseType;
    private Long lessonId;
    private String day;
    private Integer type;
    private String teacherPhoneNo;
    private String keyPoint;
    private Integer bu;
    private Integer userId;
    private String sellerName;
    private String sellerPhoneNo;
    private Integer source;
    private String grade;
}
