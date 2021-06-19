package com.codewhy.common.configuration;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class MySessionManager extends DefaultWebSessionManager {
    private static final String AUTHORIZATION = "authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "cookie";

    public MySessionManager() {
        super();
    }
    //获取sessionId从请求中
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //从请求头中获取token
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        // 判断是否有值
        if (!StringUtils.isEmpty(token)) {
            // 设置当前session状态
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        } else {
            // 若header获取不到token则尝试从cookie中获取
          return super.getSessionId(request, response);
        }
    }
}
