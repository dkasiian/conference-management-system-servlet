package com.dkasiian.model.dto;


import com.dkasiian.model.entities.Role;

public class UserDto {

    private long id;
    private String login;
    private String password;
    private String nameEn;
    private String nameUa;
    private String surnameEn;
    private String surnameUa;
    private String email;
    private Role role;

    private UserDto(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.nameEn = builder.nameEn;
        this.nameUa = builder.nameUa;
        this.surnameEn = builder.surnameEn;
        this.surnameUa = builder.surnameUa;
        this.email = builder.email;
        this.role = builder.role;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameUa() {
        return nameUa;
    }

    public String getSurnameEn() {
        return surnameEn;
    }

    public String getSurnameUa() {
        return surnameUa;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public static class Builder {

        private long id;
        private String login;
        private String password;
        private String nameEn;
        private String nameUa;
        private String surnameEn;
        private String surnameUa;
        private String email;
        private Role role;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setNameEn(String name) {
            this.nameEn = name;
            return this;
        }

        public Builder setNameUa(String name) {
            this.nameUa = name;
            return this;
        }

        public Builder setSurnameEn(String surname) {
            this.surnameEn = surname;
            return this;
        }

        public Builder setSurnameUa(String surname) {
            this.surnameUa = surname;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }

    }

    @Override
    public String toString() {
        return "UserDto{" +
                " id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", nameUa='" + nameUa + '\'' +
                ", surnameEn='" + surnameEn + '\'' +
                ", surnameUa='" + surnameUa + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
