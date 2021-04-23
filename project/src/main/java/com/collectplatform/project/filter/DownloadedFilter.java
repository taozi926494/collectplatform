package com.collectplatform.project.filter;

import com.collectplatform.project.service.ScriptFileService;
import com.collectplatform.project.service.impl.ScriptFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/23
 */
//@Component
public class DownloadedFilter  implements Filter {
//    @Autowired
//    ScriptFileService scriptFileService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse var2, FilterChain var3) throws IOException, ServletException {
        String fileId = req.getParameter("id");
        System.out.println(fileId);
        ScriptFileService service = new ScriptFileServiceImpl();
        service.deleteTempFile(fileId);
    }

    @Override
    public void destroy() {
    }
}
