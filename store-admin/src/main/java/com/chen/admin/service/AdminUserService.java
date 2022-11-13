package com.chen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.admin.param.AdminUserParam;
import com.chen.admin.pojo.AdminUser;
import com.chen.untils.R;

/**
 * 管理用户服务
 *
 * @author CHEN
 * @date 2022/11/13
 */
public interface AdminUserService extends IService<AdminUser> {
    /**
     * 登录
     *
     * @param adminUserParam 管理用户参数
     * @return {@link R}
     */
    AdminUser login(AdminUserParam adminUserParam);
}
