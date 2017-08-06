package com.sailfish.type;

/**
 * @author sailfish
 * @create 2017-08-05-下午6:16
 */
public enum Enabled {

    enabled(1),  //启用
    disabled(0);  //禁用

    private final int value;

    private Enabled(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
