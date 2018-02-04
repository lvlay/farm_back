package com.taoroot.redis.entiy;

/**
 * @author: taoroot
 * @date: 2018/1/29
 * @description: 序列号代理发送数据
 */
public class FarmAgentMessage {
    private String packetDescr;
    private String messageHead;
    private int sequence;
    private String messageType;
    private int messageLength;
    private int crc16;
    private String type;
    private int model;
    private String macAddress;
    private String token;
    private long sessionId;
    private int dId;
    private Float temperature;
    private Float humidity;

    public Float getHumidity() {
        return humidity;
    }

    public FarmAgentMessage setHumidity(Float humidity) {
        this.humidity = humidity;
        return this;
    }

    public Float getTemperature() {
        return temperature;
    }

    public FarmAgentMessage setTemperature(Float temperature) {
        this.temperature = temperature;
        return this;
    }

    public long getSessionId() {
        return sessionId;
    }

    public int getdId() {
        return dId;
    }

    public FarmAgentMessage setdId(int dId) {
        this.dId = dId;
        return this;
    }

    public FarmAgentMessage setSessionId(long sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public FarmAgentMessage setToken(String token) {
        this.token = token;
        return this;
    }

    public String getPacketDescr() {
        return packetDescr;
    }

    public void setPacketDescr(String packetDescr) {
        this.packetDescr = packetDescr;
    }

    public String getMessageHead() {
        return messageHead;
    }

    public void setMessageHead(String messageHead) {
        this.messageHead = messageHead;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public void setMessageLength(int messageLength) {
        this.messageLength = messageLength;
    }

    public int getCrc16() {
        return crc16;
    }

    public void setCrc16(int crc16) {
        this.crc16 = crc16;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

}
