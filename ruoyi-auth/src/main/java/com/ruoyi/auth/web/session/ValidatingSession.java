package com.ruoyi.auth.web.session;

import com.ruoyi.auth.web.session.exception.InvalidSessionException;

public interface ValidatingSession extends Session {

    boolean isValid();

    void validate() throws InvalidSessionException;
}

