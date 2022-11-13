package com.chen.admin.service;

import com.chen.param.CartListParam;
import com.chen.param.PageParam;
import com.chen.pojo.User;
import com.chen.untils.R;

/**
 * 用户服务
 *
 * @author CHEN
 * @date 2022/11/13
 */
public interface UserService {
    /**
     * 用户列表
     *
     * @param param 参数
     * @return {@link R}
     */
    R userList(PageParam param);

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
     * 保存
     *
     * @param user 用户
     * @return {@link R}
     */
    R save(User user);
}
