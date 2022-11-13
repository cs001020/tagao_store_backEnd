package com.chen.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.admin.mapper.AdminUserMapper;
import com.chen.admin.param.AdminUserParam;
import com.chen.admin.pojo.AdminUser;
import com.chen.admin.service.AdminUserService;
import com.chen.client.UserClient;
import com.chen.constants.UserConstants;
import com.chen.untils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理用户服务impl
 *
 * @author CHEN
 * @date 2022/11/13
 */
@Service
@Slf4j
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {


    @Override
    public AdminUser login(AdminUserParam adminUserParam) {
        //密码加密加盐
        String encodePassword = MD5Util.encode(adminUserParam.getUserPassword() + UserConstants.USER_SALT);
        //查询数据库
        AdminUser adminUser = lambdaQuery().eq(AdminUser::getUserAccount, adminUserParam.getUserAccount())
                .eq(AdminUser::getUserPassword, encodePassword).one();
        //返回结果
        log.info("AdminUserServiceImpl.login业务结束，结果:{}",adminUser);
        return adminUser;
    }
}
