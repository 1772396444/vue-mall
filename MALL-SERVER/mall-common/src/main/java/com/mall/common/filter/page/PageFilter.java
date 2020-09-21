package com.mall.common.filter.page;

import com.mall.common.bean.PageContext;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: C-aranlism create by 2020/9/21.
 * @Version 1.0
 * @Parm request
 * @Description mp前端分页拦截器
 */
public class PageFilter implements Filter {

    private Integer pageSize;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        Integer offSet = 0;
        Integer size = pageSize;
        try {
            offSet = Integer.parseInt(req.getParameter("pageNo"));
        }catch (Exception e){}
        try {
            size = Integer.parseInt(req.getParameter("pageSize"));
        }catch (Exception e){}
        try {
            PageContext.setPageSize(size);
            PageContext.setPageOffset(offSet);
            PageContext.setSort(req.getParameter("sort"));
            PageContext.setOrder(req.getParameter("order"));
            chain.doFilter(req , res);
        }catch (Exception e){
            PageContext.removeSort();
            PageContext.removeOrder();
            PageContext.removePageSize();
            PageContext.removePageOffset();
        }
    }

    @Override
    public void init(FilterConfig cfg) {
        try {
            pageSize = Integer.parseInt(cfg.getInitParameter("pageSize"));
        }catch (Exception e){
            pageSize = 15;
        }
    }

}
