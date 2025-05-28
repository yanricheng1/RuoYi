package com.ruoyi.auth.web.util;

import com.ruoyi.auth.web.session.ValidatingSession;
import com.ruoyi.auth.web.session.exception.InvalidSessionException;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class SimpleSession implements ValidatingSession, Serializable {

    private static final long serialVersionUID = 326918833808347294L;
    private transient Serializable id;
    private transient Date startTimestamp;
    private transient Date stopTimestamp;
    private transient Date lastAccessTime;
    private transient long timeout;
    private transient boolean expired;
    private transient String host;

    @Override
    public Serializable getId() {
        return null;
    }

    @Override
    public Date getStartTimestamp() {
        return null;
    }

    @Override
    public Date getLastAccessTime() {
        return null;
    }

    @Override
    public long getTimeout() throws InvalidSessionException {
        return 0;
    }

    @Override
    public void setTimeout(long maxIdleTimeInMillis) throws InvalidSessionException {

    }

    @Override
    public String getHost() {
        return null;
    }

    @Override
    public void touch() throws InvalidSessionException {

    }

    @Override
    public void stop() throws InvalidSessionException {

    }

    @Override
    public Collection<Object> getAttributeKeys() throws InvalidSessionException {
        return null;
    }

    @Override
    public Object getAttribute(Object key) throws InvalidSessionException {
        return null;
    }

    @Override
    public void setAttribute(Object key, Object value) throws InvalidSessionException {

    }

    @Override
    public Object removeAttribute(Object key) throws InvalidSessionException {
        return null;
    }


    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void validate() throws InvalidSessionException {

    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Date getStopTimestamp() {
        return stopTimestamp;
    }

    public void setStopTimestamp(Date stopTimestamp) {
        this.stopTimestamp = stopTimestamp;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
