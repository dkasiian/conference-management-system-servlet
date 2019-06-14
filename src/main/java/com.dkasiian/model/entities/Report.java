package com.dkasiian.model.entities;

import java.time.LocalDateTime;

public class Report {

    private long id;
    private String theme;
    private LocalDateTime dateTime;
    private long speakerId;

    private Report(Builder builder) {
        this.id = builder.id;
        this.theme = builder.theme;
        this.dateTime = builder.dateTime;
        this.speakerId = builder.speakerId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getTheme() {
        return theme;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public long getSpeakerId() {
        return speakerId;
    }

    @Override
    public String toString() {
        return "Report{" +
                " id='" + id + '\'' +
                ", theme='" + theme + '\'' +
                ", datetime='" + dateTime + '\'' +
                ", speakerId='" + speakerId + "\'" +
                '}';
    }

    public static class Builder {
        private long id;
        private String theme;
        private LocalDateTime dateTime;
        private long speakerId;

        public Report build() {
            return new Report(this);
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setTheme(String theme) {
            this.theme = theme;
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
