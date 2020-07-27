package com.vtb.test.service;

import java.util.Objects;

public class TestService {
    private String name;
    private String info;

    public TestService() {
    }

    public TestService(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestService that = (TestService) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, info);
    }

    @Override
    public String toString() {
        return "TestService{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
