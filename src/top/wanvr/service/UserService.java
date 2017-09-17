package top.wanvr.service;

import top.wanvr.entity.User;

public interface UserService {
    //根据用户名查找
    User getUserByCodePassword(User user);

    //添加用户
    int saveUser(User user);
}
