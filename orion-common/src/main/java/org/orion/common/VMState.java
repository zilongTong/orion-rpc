package org.orion.common;

/**
 * @ClassName VMState
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/16 11:22
 **/

public enum VMState {

    FULL(1, "满载"), IDLE(2, "空闲"), COLD(3, "冷却");
    int code;
    String desc;

    VMState(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }}
