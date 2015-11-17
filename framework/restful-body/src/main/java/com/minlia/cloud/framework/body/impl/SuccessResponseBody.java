package com.minlia.cloud.framework.body.impl;

import com.minlia.cloud.framework.body.constants.BodyState;

/**
 * Created by user on 11/14/15.
 */
public class SuccessResponseBody extends StatefulResponseBody {
    public SuccessResponseBody(){
        this.setMessage(BodyState.Success_Message);
        this.setState(BodyState.Success);
    }

}
