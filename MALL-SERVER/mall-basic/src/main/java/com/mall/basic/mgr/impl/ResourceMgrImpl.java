package com.mall.basic.mgr.impl;

import com.mall.basic.mgr.ResourceMgr;
import com.mall.basic.service.ResourceService;
import com.mall.entity.basic.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: C-aranlism create by 2020/9/24.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ResourceMgrImpl implements ResourceMgr {

    @Autowired
    ResourceService resourceService;

    @Override
    @Transactional(readOnly = true)
    public List<Resource> menuList(String roleId){
        return resourceService.menuList(roleId);
    }

    @Override
    public String createId(Integer type , String parentId){

        StringBuffer sb = new StringBuffer();
        boolean next = false;
        String maxId = resourceService.maxId(type , parentId);

        // 当前 parentId 下没有子资源，根据 parentId 生成新ID,用 next 标识当前最大ID是父ID
        if(StringUtils.isEmpty(maxId)){
            maxId = parentId;
            next = true;
        }

        Integer first = Integer.valueOf(maxId.substring(0,2));
        Integer second = Integer.valueOf(maxId.substring(2,5));
        Integer third = Integer.valueOf(maxId.substring(5,8));
        Integer btn = Integer.valueOf(maxId.substring(8));

        // 第一层(处理根ID)
        if(first != 0 && second == 0 && third == 0 && type == 1){
            first += 1;
            sb.append((String.valueOf(first).length() == 1) ? ("0" + first) : first);
        }else {
            sb.append(maxId.substring(0,2));
        }

        // 第二层(处理二级ID)
        if(second != 0 && third == 0 && type == 1){
            if(next){
                third += 1;
                sb.append(maxId.substring(2,5));
            }else{
                second += 1;
                int length = String.valueOf(second).length();
                sb.append((length == 1) ? ("00" + second) : (length == 2 ? "0" + second : second + ""));
            }
        }else {
            sb.append(maxId.substring(2,5));
        }

        // 第三层(处理三级ID)
        if(third != 0 && type == 1){
            if(!next){
                third += 1;
            }
            int length = String.valueOf(third).length();
            sb.append((length == 1) ? ("00" + third) : (length == 2 ? "0" + third : "" + third));
        }else {
            sb.append(maxId.substring(5,8));
        }

        // 按钮ID,如果是菜单就取默认的 00
        if(type == 1){
            sb.append("00");
        }else {
            btn += 1;
            sb.append((String.valueOf(btn).length() == 1) ? ("0" + btn) : btn);
        }

        return sb.toString();
    }

}
