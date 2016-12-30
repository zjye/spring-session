package org.zjye.zession;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class SessionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getParameter("_s")==null) {
            StringBuffer url = request.getRequestURL().append("?");
            if(!StringUtils.isEmpty(request.getQueryString()))
                url.append("&");
            url.append("_s=");
            url.append(UUID.randomUUID().toString().replace("-",""));
            response.sendRedirect(url.toString());
        }
        filterChain.doFilter(request, response);
    }
}
