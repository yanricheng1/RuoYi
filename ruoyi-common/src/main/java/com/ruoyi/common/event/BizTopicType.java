package com.ruoyi.common.event;

public enum BizTopicType {
    USER_LOGIN("user_login"),
    PASSWORD_ERROR("user_login_password"),
    USER_REGISTER("user_register"),
    ;
    private String topic;
    BizTopicType(String topic) {
        this.topic = topic;
    }
    public String getTopic() {
        return topic;
    }
}
