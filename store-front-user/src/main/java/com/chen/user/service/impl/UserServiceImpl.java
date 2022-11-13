package com.chen.user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.constants.UserConstants;
import com.chen.param.CartListParam;
import com.chen.param.PageParam;
import com.chen.param.UserCheckParam;
import com.chen.param.UserLoginParam;
import com.chen.pojo.User;
import com.chen.untils.MD5Util;
import com.chen.untils.R;
import com.chen.user.Mapper.UserMapper;
import com.chen.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用户服务
 *
 * @author 10065
 * @date 2022/11/10
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService  {
    @Override
    public R check(UserCheckParam userCheckParam){
        //数据库查询
        Long total = lambdaQuery().eq(User::getUserName, userCheckParam.getUserName()).count();
        //查询结果处理
        if (total==0){
            log.info("UserServiceImpl.check业务结束，结果:{}","账号可用!");
            return R.ok("账号不存在可以使用");
        }
        log.info("UserServiceImpl.check业务结束，结果:{}","账号不可用");
        return R.fail("账号已经存在，不可注册");
    }

    @Override
    public R register(User user) {
        //检查账号是否存在
        Long total = lambdaQuery().eq(User::getUserName, user.getUserName()).count();
        //查询结果处理
        if (total!=0){
            log.info("UserServiceImpl.register业务结束，结果:{}","账号存在注册失败");
            return R.ok("账号已经存在不可注册！");
        }
        //密码加密
        String password = MD5Util.encode(user.getPassword()+ UserConstants.USER_SALT);
        user.setPassword(password);
        //插入数据库
        int insertCount = baseMapper.insert(user);
        //返回结果封装
        if (insertCount==0){
            log.info("UserServiceImpl.register业务结束，结果:{}","数据库插入失败！注册失败！");
            return R.fail("注册失败！请稍微再试！");
        }
        log.info("UserServiceImpl.register业务结束，结果:{}","注册成功！");
        return R.ok("注册成功！");
    }

    @Override
    public R login(UserLoginParam userLoginParam) {
        //数据库查询
        User loginUser = lambdaQuery().eq(User::getUserName, userLoginParam.getUserName()).one();
        //账号不存在
        if (Objects.isNull(loginUser)){
            return R.fail("账号或密码错误!");
        }
        //密码加密加盐比较
        String encodePassword = MD5Util.encode(userLoginParam.getPassword() + UserConstants.USER_SALT);
        //密码错误
        if (!encodePassword.equals(loginUser.getPassword())){
            return R.fail("账号或密码错误！");
        }
        //密码正确登陆成功
        log.info("UserServiceImpl.login业务结束，结果:{}","登陆成功");
        //为null 不会进行序列化
        loginUser.setPassword(null);
        loginUser.setUserPhonenumber(null);
        //返回结果
        return R.ok("登陆成功！",loginUser);
    }

    @Override
    public R listPage(PageParam param) {
        Page<User> page = lambdaQuery().page(new Page<>(param.getCurrentPage(), param.getPageSize()));
        R ok = R.ok("查询成功", page.getRecords(), page.getTotal());
        log.info("UserServiceImpl.listPage业务结束，结果:{}",ok);
        return ok;
    }

    @Override
    public R remove(CartListParam cartListParam) {
        boolean b = removeById(cartListParam.getUserId());
        log.info("UserServiceImpl.remove业务结束，结果:{}",b);
        return R.ok("用户数据删除成功");
    }

    @Override
    public R update(User user) {
        Long count = lambdaQuery().eq(User::getUserId, user.getUserId())
                .eq(User::getPassword, user.getPassword()).count();

        if (count==0){
            String encodePassword = MD5Util.encode(user.getPassword() + UserConstants.USER_SALT);
            user.setPassword(encodePassword);
        }
        boolean b = updateById(user);
        log.info("UserServiceImpl.update业务结束，结果:{}",b);
        return R.ok("用户信息修改成功!");
    }

    @Override
    public R sava(User user) {
        //检查账号是否存在
        Long total = lambdaQuery().eq(User::getUserName, user.getUserName()).count();
        //查询结果处理
        if (total!=0){
            log.info("UserServiceImpl.register业务结束，结果:{}","账号存在注册失败");
            return R.ok("账号已经存在不可添加！");
        }
        //密码加密
        String password = MD5Util.encode(user.getPassword()+ UserConstants.USER_SALT);
        user.setPassword(password);
        //插入数据库
        int insertCount = baseMapper.insert(user);
        //返回结果封装
        if (insertCount==0){
            log.info("UserServiceImpl.register业务结束，结果:{}","数据库插入失败！添加失败！");
            return R.fail("添加失败！请稍微再试！");
        }
        log.info("UserServiceImpl.register业务结束，结果:{}","添加成功！");
        return R.ok("添加成功！");
    }
}
