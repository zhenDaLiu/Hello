package com.yuntongxun.ytx.fast.constenum;

/**
 * @author luocc
 */
public enum ConstEnum {

    STATUS_ENABLE(1, "有效"),
    STATUS_DISABLE(2, "无效");

    private Integer value;
    private String name;

    ConstEnum(Integer value, String name) {
        this.name = name;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ConstEnum get(String value) {
        ConstEnum[] values = ConstEnum.values();
        for (ConstEnum object : values) {
            if (object.value.equals(value)) {
                return object;
            }
        }
        return null;
    }
}
