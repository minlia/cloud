package com.minlia.cloud.framework.body.impl;

import com.minlia.cloud.framework.body.constants.BodyState;

/**
 * Created by user on 11/14/15.
 */
public class FailureResponseBody extends StatefulResponseBody {
    public FailureResponseBody(){
        this.setMessage(BodyState.Failure_Message);
        this.setState(BodyState.Failure);
    }

}
