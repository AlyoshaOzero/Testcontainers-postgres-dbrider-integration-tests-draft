package com.example.demo.api;

import java.util.Objects;

public class SomeObject implements Cloneable {

    private long limit;
    private String message;

    public SomeObject() {}

    public SomeObject(long limit, String message) {
        this.limit = limit;
        this.message = message;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    protected SomeObject clone() {
        return new SomeObject(this.limit, this.message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SomeObject that = (SomeObject) o;
        return limit == that.limit && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(limit, message);
    }
}
