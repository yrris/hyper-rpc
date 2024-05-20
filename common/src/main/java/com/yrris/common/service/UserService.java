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
}
