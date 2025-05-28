package com.ruoyi.web.controller.system;

import com.ruoyi.auth.web.authz.UsernamePasswordToken;
import com.ruoyi.auth.web.sso.manager.LoginManager;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.UserUtils;
import com.ruoyi.common.utils.context.Account;
import com.ruoyi.common.utils.context.AppThreadContext;
import com.ruoyi.common.utils.context.ContextConstants;
import com.ruoyi.framework.web.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@Controller
public class SysLoginController extends BaseController {
    /**
     * 是否开启记住我功能
     */
    @Value("${shiro.rememberMe.enabled: false}")
    private boolean rememberMe;

    /**
     * 验证码开关
     */
    @Value("${shiro.user.captchaEnabled:true}")
    private boolean captchaEnabled;

    /**
     * 验证码类型
     */
    @Value("${shiro.user.captchaType:math}")
    private String captchaType;


    @Autowired
    private ConfigService configService;
    @Resource
    private LoginManager loginManager;


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap mmap) {

        Object obj = request.getSession().getAttribute(ContextConstants.AUTH_ACCOUNT_KEY);
        if (obj != null) {
            Account account = (Account) obj;
            loginManager.logout(account.toSysUser());
            request.getSession().removeAttribute(ContextConstants.AUTH_ACCOUNT_KEY);
            AppThreadContext.unbindAccount();
        }

        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request)) {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        setAttribute(request, response, mmap);
        return "login";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap mmap) {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request)) {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        setAttribute(request, response, mmap);
        return "login";
    }


    @RequestMapping(value = {"/relogin"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String reLogin(HttpServletRequest request, HttpServletResponse response, ModelMap mmap) {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request)) {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        setAttribute(request, response, mmap);
        return "login";
    }


    private void setAttribute(HttpServletRequest request, HttpServletResponse response, ModelMap mmap) {
        //是否展示验证码
        mmap.put("captchaEnabled", captchaEnabled);
        mmap.put("captchaType", captchaType);
        // 是否开启记住我
        mmap.put("isRemembered", rememberMe);
        // 是否开启用户注册
        mmap.put("isAllowRegister", Convert.toBool(configService.getKey("sys.account.registerUser"), false));
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password,
                                boolean rememberMe,
                                HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
//            request.getRequestDispatcher("/login").forward(request, response);
//            return null;
            return AjaxResult.error("服务器超时，请重新登录");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        loginManager.login(token);
        Account account = UserUtils.getAccount();
        if (account != null) {
            request.getSession().setAttribute(ContextConstants.AUTH_ACCOUNT_KEY, account);
        }
        return success();
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "error/unauth";
    }
}
