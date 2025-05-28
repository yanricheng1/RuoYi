package com.ruoyi.auth.web.helper;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.AjaxResult;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseHelper {
    public static void flushBizError(ServletResponse response, AjaxResult result) {
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter printWriter = null;
            printWriter = response.getWriter();
            printWriter.write(JSON.toJSONString(result));
            printWriter.flush();
        } catch (IOException e) {
            //ignore
        }
    }
}
