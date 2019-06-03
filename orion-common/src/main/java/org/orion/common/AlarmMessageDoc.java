package org.orion.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author leo
 */
@Data
@Builder
public class AlarmMessageDoc {
    /**
     * id
     */
    private String id;
    /**
     * id
     */
    private String env;
    /**
     * 告警指标code
     */
    private String metricCode;
    /**
     * 告警指标名称
     */
    private String metricName;

    /**
     * 标题
     */
    private String details;

    /**
     * 脱敏后标题
     */
    private String title;

    private String alarmRuleId;
    /**
     * 告警时间
     */
    private String time;

    /**
     * 严重等级
     */
    private String level;

    /**
     * 监控大类
     */
    private String majorType;

    /**
     * 业务监控子类
     */
    private String subType;

    /**
     * apollo appid
     */
    private String appId;

    /**
     * 服务名
     */
    private String serviceName;

}
