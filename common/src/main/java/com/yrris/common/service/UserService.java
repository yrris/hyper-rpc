package com.yrris.common.service;

import com.yrris.common.model.User;

/**
 * 用户服务
 */
public interface UserService {
    /**
     * 获取用户
     * @param user
     * @return 用户
     */
    User getUser(User user);

    /**
     * 获取数字 用于测试Mock调用
     * @return 默认返回数字1
     */
    default int getNumber(){
        return 1;
    }
}
