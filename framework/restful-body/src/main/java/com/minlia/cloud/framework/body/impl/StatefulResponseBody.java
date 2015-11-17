package com.minlia.cloud.framework.body.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.cloud.framework.body.ResponseBody;
import com.minlia.cloud.framework.body.StatefulBody;
import com.minlia.cloud.framework.body.constants.BodyState;

/**
 * Created by user on 11/14/15.
 */
public class StatefulResponseBody implements StatefulBody, ResponseBody {

    protected BodyState state;
    protected String message;


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    @JsonIgnore
    public BodyState getState() {
        return state;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setState(BodyState state) {
        this.state = state;
    }

    @JsonProperty(value = "state")
    public Integer getStateOridinal() {
        return state.ordinal();
    }

    public void setState(Integer state) {
        this.state = BodyState.valueOf(state.toString());
    }

    public void setState(String state) {
        this.state = BodyState.valueOf(state.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatefulResponseBody that = (StatefulResponseBody) o;

        if (state != that.state) return false;
        return !(message != null ? !message.equals(that.message) : that.message != null);

    }

    @Override
    public int hashCode() {
        int result = state != null ? state.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StatefulResponseBody{" +
                "state=" + state +
                ", message='" + message + '\'' +
                '}';
    }
}
