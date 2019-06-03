package org.orion.protocol;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName ProtocolEnum
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/22 11:22
 **/
@Getter
@AllArgsConstructor
public enum ProtocolEnum {

    TCP(1, "TCP"), HTTP(2, "HTTP");
    int code;
    String desc;

    public static ProtocolEnum getTypeByDesc(String desc) {
        for (ProtocolEnum type : ProtocolEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc))
                return type;
        }
        return HTTP;
    }
}
