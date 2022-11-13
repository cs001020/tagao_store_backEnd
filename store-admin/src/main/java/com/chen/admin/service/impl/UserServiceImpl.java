package com.chen.admin.service.impl;

import com.chen.admin.service.UserService;
import com.chen.client.UserClient;
import com.chen.param.CartListParam;
import com.chen.param.PageParam;
import com.chen.pojo.User;
import com.chen.untils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户服务impl
 *
 * @author CHEN
 * @date 2022/11/13
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserClient userClient;
    @Override
    @Cacheable(value = "list.user",key = "#param.currentPage+'-'+#param.pageSize")
    public R userList(PageParam param) {
        R r = userClient.listPage(param);
        log.info("UserServiceImpl.userList业务结束，结果:{}",r);
        return r;
    }

    @Override
    @CacheEvict(value = "list.user",allEntries = true)
    public R remove(CartListParam cartListParam) {
        R r = userClient.adminRemove(cartListParam);
        log.info("UserServiceImpl.remove业务结束，结果:{}",r);
        return r;
    }

    @Override
    @CacheEvict(value = "list.user",allEntries = true)
    public R update(User user) {
        R r = userClient.adminUpdate(user);
        log.info("UserServiceImpl.update业务结束，结果:{}",r);
        return r;
    }

    @Override
    @CacheEvict(value = "list.user",allEntries = true)
    public R save(User user) {
        R r = userClient.adminSave(user);
        log.info("UserServiceImpl.save业务结束，结果:{}",r);
        return r;
    }
}
