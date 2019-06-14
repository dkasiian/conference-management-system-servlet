package com.dkasiian.model.entities;

import java.time.LocalDateTime;

public class Conference {

    private long id;
    private String name;
    private LocalDateTime dateTime;
    private String location;

    private Conference(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.dateTime = builder.dateTime;
        this.location = builder.location;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Report{" +
                " id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", datetime='" + dateTime + '\'' +
                ", location='" + location + "\'" +
                '}';
    }

    public static class Builder {
        private long id;
        private String name;
        private LocalDateTime dateTime;
        private String location;

        public Conference build() {
            return new Conference(this);
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null || obj.getClass() != this.getClass()) {
//            return false;
//        }
//        if (this == obj) {
//            return true;
//        }
//        Conference conference = (Conference) obj;
//        return this.id == conference.id &&
//                this.name.equals(conference.name) &&
//                this.location.equals(conference.location) &&
//                this.dateTime.equals(conference.dateTime);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, location, dateTime);
//    }
}
