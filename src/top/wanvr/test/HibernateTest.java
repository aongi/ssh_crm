package top.wanvr.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.wanvr.dao.UserDao;
import top.wanvr.entity.User;
import top.wanvr.service.UserService;

import javax.annotation.Resource;

/*测试hibernate框架*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applocationContext.xml")
public class HibernateTest {

    @Resource(name = "sessionFactory")
    private SessionFactory sf;


    @Test
    public void fun1(){
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //--------------------------------------
        User user = new User();
        user.setUser_code("tom");
        user.setUser_name("汤姆");
        user.setUser_password("1234");
        session.save(user);
        //--------------------------------------
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void fun2(){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        //--------------------------------------
        User user = new User();
        user.setUser_code("zhaoqi");
        user.setUser_name("朝气");
        user.setUser_password("1234");
        session.save(user);
        //--------------------------------------
        transaction.commit();
        session.close();
    }

    @Resource(name = "userDao")
    private UserDao ud;

    @Test
    public void fun3(){
        User tom = ud.getByUserCode("tom");
        System.out.println(tom);
    }

    @Resource(name = "us")
    private UserService us;

    //测试AOP事务
    @Test
    public void fun4(){
        User user = new User();
        user.setUser_code("wangwu");
        user.setUser_name("王五");
        user.setUser_password("1234");
        us.saveUser(user);
    }
}
