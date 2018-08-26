package com.finleap.rahulshekharweather.model;

public class TimezoneDetails {

    Long dstOffset;
    Long rawOffset;
    String status;
    String timeZoneId;
    String timeZoneName;

    public Long getDstOffset() {
        return dstOffset;
    }

    public void setDstOffset(Long dstOffset) {
        this.dstOffset = dstOffset;
    }

    public Long getRawOffset() {
        return rawOffset;
    }

    public void setRawOffset(Long rawOffset) {
        this.rawOffset = rawOffset;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getTimeZoneName() {
        return timeZoneName;
    }

    public void setTimeZoneName(String timeZoneName) {
        this.timeZoneName = timeZoneName;
    }
}
