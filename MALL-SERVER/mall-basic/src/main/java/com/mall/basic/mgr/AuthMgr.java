package com.mall.basic.mgr;

import com.mall.basic.from.AuthFrom;
import com.mall.entity.basic.User;

/**
 * @Author: C-aranlism create by 2020/9/22.
 * @Version 1.0
 * @Parm request
 * @Description
 */
public interface AuthMgr {

    User loginValid(AuthFrom from);

}
