package com.cyyaw.config.filter;

import cn.hutool.core.util.StrUtil;
import com.cyyaw.util.tools.JwtTokenUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Configuration
public class TokenFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String loginInfo = null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest rq = (HttpServletRequest) request;
            String token = rq.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                loginInfo = JwtTokenUtils.getClaim(token);
            }
        }
        ThreadLocalContext.setLoginInfo(loginInfo);
        filterChain.doFilter(request, response);
    }
}
