package com.ruoyi.common.event;

import java.util.HashMap;
import java.util.Map;

public class BizEventDispatcher {

    private static final Map<String, BizEventParser> parsers = new HashMap<>();

    public static void dispatch(BizTopicType topic, Object event) {
        BizEventParser parser = parsers.get(topic.getTopic());
        if (parser != null) {
            parser.parse(event);
        }
    }

    public static void register(String topic, BizEventParser parser) {
        parsers.put(topic, parser);
    }

}
