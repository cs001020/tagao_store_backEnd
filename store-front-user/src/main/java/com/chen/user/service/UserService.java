package com.chen.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.param.CartListParam;
import com.chen.param.PageParam;
import com.chen.param.UserCheckParam;
import com.chen.param.UserLoginParam;
import com.chen.pojo.User;
import com.chen.untils.R;

/**
 * 用户服务
 *
 * @author 10065
 * @date 2022/11/10
 */
public interface UserService extends IService<User> {

    /**
     * 检查账号是否可用
     *
     * @param userCheckParam 用户检查参数
     * @return {@link R}
     */
    R check(UserCheckParam userCheckParam);

    /**
     * 注册
     *
     * @param user 用户
     * @return {@link R}
     */
    R register(User user);

    /**
     * 登录
     *
     * @param userLoginParam 用户登录参数
     * @return {@link R}
     */
    R login(UserLoginParam userLoginParam);

    /**
     * 列表页面
     *
     * @param param 参数
     * @return {@link R}
     */
    R listPage(PageParam param);

    /**
     * 删除
     *
     * @param cartListParam 购物车列表参数
     * @return {@link R}
     */
    R remove(CartListParam cartListParam);

    /**
     * 更新
     *
     * @param user 用户
     * @return {@link R}
     */
    R update(User user);

    /**
     * 萨瓦河
     *
     * @param user 用户
     * @return {@link R}
     */
    R sava(User user);
}
