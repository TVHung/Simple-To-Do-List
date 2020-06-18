package com.example.model;

import java.io.Serializable;

public class Todo implements Serializable {
    private String tenCongViec;
    private String moTaCongViec;
    private String thoiHan;
    private String thoiGian;

    public Todo(String tenCongViec, String moTaCongViec, String thoiHan, String thoiGian) {
        this.tenCongViec = tenCongViec;
        this.moTaCongViec = moTaCongViec;
        this.thoiHan = thoiHan;
        this.thoiGian = thoiGian;
    }

    public Todo() {
    }

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    public String getMoTaCongViec() {
        return moTaCongViec;
    }

    public void setMoTaCongViec(String moTaCongViec) {
        this.moTaCongViec = moTaCongViec;
    }

    public String getThoiHan() {
        return thoiHan;
    }

    public void setThoiHan(String thoiHan) {
        this.thoiHan = thoiHan;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
}
