package com.togo.accounting.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.togo.accounting.exception.BizErrorCode;
import com.togo.accounting.exception.ErrorResponse;
import com.togo.accounting.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * create by crashLab on 2020/05/16.
 **/
@Component
@Slf4j
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomFormAuthenticationFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                return executeLogin(request, response);
            } else {

                //allow them to see the login page ;)
                return true;
            }
        } else {
            saveRequest(request);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            val errorResponse= ErrorResponse.builder()
                                            .code(BizErrorCode.NO_AUTHORIZED)
                                            .errorType(ServiceException.ErrorType.Client)
                                            .statusCode(HttpStatus.UNAUTHORIZED.value())
                                            .message("No access for related url")
                                            .build();
            try (PrintWriter writer = response.getWriter()){
                   writer.write(objectMapper.writeValueAsString(errorResponse));
               } catch (IOException e) {
                log.debug("The IO exception is thrown");
                return false;
            }


            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED,"No access related url");
            return false;
        }
    }
}
