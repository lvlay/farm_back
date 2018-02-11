package com.taoroot.pojo;

public class Threshold {
    private Long id;

    private Integer deviceId;

    private Integer type;

    private Integer expression;

    private Integer value;

    public Threshold(Long id, Integer deviceId, Integer type, Integer expression, Integer value) {
        this.id = id;
        this.deviceId = deviceId;
        this.type = type;
        this.expression = expression;
        this.value = value;
    }

    public Threshold() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getExpression() {
        return expression;
    }

    public void setExpression(Integer expression) {
        this.expression = expression;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}