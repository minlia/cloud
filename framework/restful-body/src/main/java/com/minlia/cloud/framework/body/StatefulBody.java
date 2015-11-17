package com.minlia.cloud.framework.body;

import com.minlia.cloud.framework.body.constants.BodyState;

/**
 * Created by user on 11/14/15.
 */
public abstract interface StatefulBody extends Body {
    public String getMessage();

    public BodyState getState();

    public void setMessage(String message);

    public void setState(BodyState state);

    public Integer getStateOridinal();

    public void setState(Integer state);

    public void setState(String state);


}
