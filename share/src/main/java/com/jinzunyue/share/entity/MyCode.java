package com.jinzunyue.share.entity;

public class MyCode {
    private String area;

    private String kind;

    private String date;

    private String type;

    private Integer n;

    @Override
    public String toString() {
        return "Code{" +
                "area='" + area + '\'' +
                ", kind='" + kind + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", n=" + n +
                '}';
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }
}
