package top.wanvr.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.wanvr.dao.UserDao;
import top.wanvr.entity.User;
import top.wanvr.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private UserDao userDao;

    //根据用户名查找
    @Override
    public User getUserByCodePassword(User user) {
        //根据登陆名称查询登陆用户
        User byUserCode = userDao.getByUserCode(user.getUser_code());
        //判断用户是否存在，不存在=抛出异常，提示用户名不存在
        if(byUserCode==null){
            throw new RuntimeException("用户名不存在");
        }
        //判断用户密码是否正确，不正确=抛出异常，提示密码错误
        if(!byUserCode.getUser_password().equals(user.getUser_password())){
            throw new RuntimeException("密码错误！");
        }
        //返回查询到的用户对象
        return byUserCode;
    }


    //保存用户及注解事务
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public int saveUser(User user) {
        userDao.save(user);
        return 0;
    }
}
