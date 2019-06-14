package com.dkasiian.model.dto;

import java.time.LocalDateTime;

public class ReportDto {

    private long id;
    private String themeEn;
    private String themeUa;
    private LocalDateTime dateTime;
    private long speakerId;

    private ReportDto(Builder builder) {
        this.id = builder.id;
        this.themeEn = builder.themeEn;
        this.themeUa = builder.themeUa;
        this.dateTime = builder.dateTime;
        this.speakerId = builder.speakerId;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public long getId() {
        return id;
    }

    public String getThemeEn() {
        return themeEn;
    }

    public String getThemeUa() {
        return themeUa;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public long getSpeakerId() {
        return speakerId;
    }

    @Override
    public String toString() {
        return "ReportDto{" +
                " id='" + id + '\'' +
                ", themeEn='" + themeEn + '\'' +
                ", themeUa='" + themeUa + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", speakerId='" + speakerId + '\'' +
                '}';
    }

    public static class Builder {

        private long id;
        private String themeEn;
        private String themeUa;
        private LocalDateTime dateTime;
        private long speakerId;

        public ReportDto build() {
            return new ReportDto(this);
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setThemeEn(String theme) {
            this.themeEn = theme;
            return this;
        }

        public Builder setThemeUa(String themeUa) {
            this.themeUa = themeUa;
            return this;
        }

        public Builder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder setSpeakerId(long speakerId) {
            this.speakerId = speakerId;
            return this;
        }
    }

}
