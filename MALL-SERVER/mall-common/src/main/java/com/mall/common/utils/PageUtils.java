package com.mall.common.utils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.bean.PageContext;
import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/21
 */
public class PageUtils {

    public static Page getPage(){
        Page page = new Page(PageContext.getPageOffset() , PageContext.getPageSize());
        setPage(page);
        return page;
    }

    public static Page getPage(@NotNull Integer current, @NotNull Integer size){
        Page page = new Page(current , size);
        setPage(page);
        return page;
    }

    private static void setPage(Page page){
        String sort = PageContext.getSort();
        String order = PageContext.getOrder();
        if(StringUtils.isNoneBlank(sort)){
            if(StringUtils.isNotBlank(order)){
                if(PageContext.ORDER_ASC.equals(order.toUpperCase())){
                    page.addOrder(Arrays.stream(sort.split(",")).map(OrderItem::asc).collect(Collectors.toList()));
                }else{
                    page.addOrder(Arrays.stream(sort.split(",")).map(OrderItem::desc).collect(Collectors.toList()));
                }
            }else{
                page.addOrder(Arrays.asList(sort.split(",")));
            }
        }else {
            page.addOrder(OrderItem.asc(" (select 1) "));
        }
    }

}
