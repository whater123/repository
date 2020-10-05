package com.together.pojo;

import java.util.Objects;

/**
 * @author w
 */
public class StateCode {
    private String state;
    private String context;

    @Override
    public String toString() {
        return "StateCode{" +
                "state='" + state + '\'' +
                ", context='" + context + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StateCode stateCode = (StateCode) o;
        return state.equals(stateCode.state) &&
                context.equals(stateCode.context);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, context);
    }

    public StateCode(String state, String context) {
        this.state = state;
        this.context = context;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
