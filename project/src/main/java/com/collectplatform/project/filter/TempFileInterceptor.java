package com.collectplatform.project.filter;

import com.collectplatform.project.service.ScriptFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/25
 */
@Component
public class TempFileInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    ScriptFileService scriptFileService;

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        String uri = request.getRequestURI();
        if (uri.equals("/project/file/download")) {
            String url = request.getRequestURL().toString();
            String qs = request.getQueryString();
            String fullUrl = url + "?" + qs;

            MultiValueMap<String, String> queryParams =
                    UriComponentsBuilder.fromUriString(fullUrl).build().getQueryParams();

            String fileId = queryParams.get("id").get(0);
            scriptFileService.deleteTempFile(fileId);
        }
    }
}
