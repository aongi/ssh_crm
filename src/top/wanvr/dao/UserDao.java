package top.wanvr.dao;

import top.wanvr.entity.User;

public interface UserDao extends BaseDao<User> {
    //根据登陆名称查询User对象
    User getByUserCode(String userCode);

}
