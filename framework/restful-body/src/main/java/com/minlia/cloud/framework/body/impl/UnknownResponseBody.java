package com.minlia.cloud.framework.body.impl;

import com.minlia.cloud.framework.body.constants.BodyState;

/**
 * Created by user on 11/14/15.
 */
public class UnknownResponseBody extends StatefulResponseBody {
    public UnknownResponseBody(){
        this.setMessage(BodyState.Unknown_Message);
        this.setState(BodyState.Unknown);
    }

}
