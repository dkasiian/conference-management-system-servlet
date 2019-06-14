package com.dkasiian.model.dto;

import java.time.LocalDateTime;

public class ConferenceDto {

    private long id;
    private String nameEn;
    private String nameUa;
    private String locationEn;
    private String locationUa;
    private LocalDateTime dateTime;

    private ConferenceDto(Builder builder) {
        this.id = builder.id;
        this.nameEn = builder.nameEn;
        this.nameUa = builder.nameUa;
        this.locationEn = builder.locationEn;
        this.locationUa = builder.locationUa;
        this.dateTime = builder.dateTime;
    }

    public ConferenceDto() {}

//    public void setId(long id) {
//        this.id = id;
//    }

    public long getId() {
        return id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameUa() {
        return nameUa;
    }

    public String getLocationEn() {
        return locationEn;
    }

    public String getLocationUa() {
        return locationUa;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public static class Builder {

        private long id;
        private String nameEn;
        private String nameUa;
        private String locationEn;
        private String locationUa;
        private LocalDateTime dateTime;

        public ConferenceDto build() {
            return new ConferenceDto(this);
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setNameEn(String nameEn) {
            this.nameEn = nameEn;
            return this;
        }

        public Builder setNameUa(String nameUa) {
            this.nameUa = nameUa;
            return this;
        }

        public Builder setLocationEn(String locationEn) {
            this.locationEn = locationEn;
            return this;
        }

        public Builder setLocationUa(String locationUa) {
            this.locationUa = locationUa;
            return this;
        }

        public Builder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }
    }

}
