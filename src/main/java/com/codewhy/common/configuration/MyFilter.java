package com.codewhy.common.configuration;

import com.alibaba.fastjson.JSON;
import com.codewhy.vo.ResultVO;
import org.apache.shiro.web.filter.authc.UserFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MyFilter extends UserFilter {
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(JSON.toJSON(new ResultVO(400,"未登录")));
    }


}
