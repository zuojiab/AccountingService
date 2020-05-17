package com.togo.accounting.shiro;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * create by crashLab on 2020/05/16.
 **/
@Slf4j
@Component
public class CustomHttpFilter extends PermissionsAuthorizationFilter {

    @Override
    protected boolean pathsMatch(String path, ServletRequest request) {
        String currentPath = getPathWithinApplication(request);
        log.debug("Path in CustomHttpFilter : {},currentPath : {}",path,currentPath);
        val array = path.split("::");
        val url = array[0];
        boolean isHttpMethodMatched = true;
        if (array.length > 1) {
            val methodInRequest = ((HttpServletRequest)request).getMethod().toUpperCase();
            String method = array[1];
            isHttpMethodMatched = method.equals(methodInRequest);
        }
        return pathsMatch(url,currentPath) && isHttpMethodMatched;
    }
}
