package top.wanvr.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import top.wanvr.dao.UserDao;
import top.wanvr.entity.User;


//HibernateDaoSupport 为Dao注入sessionFactory
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    //根据姓名查询
    public User getByUserCode(String userCode) {
         //HQL
         return getHibernateTemplate().execute(new HibernateCallback<User>() {
             @Override
             public User doInHibernate(Session session) throws HibernateException {
                 String hql="from User where user_code=?";
                 Query query = session.createQuery(hql);
                 query.setParameter(0,userCode);
                 User user = (User) query.uniqueResult();
                 return user;
             }
         });
        //Criteria
        /*DetachedCriteria dc= DetachedCriteria.forClass(User.class);
        dc.add(Restrictions.eq("user_code",userCode));
        List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
        if(list != null && list.size()>0){
            return list.get(0);
        }else {
            return null;
        }*/

        /*//原生sql
        String sql = "select * from sys_user WHERE user_code=:user_code";
        Session s = this.getHibernateTemplate().getSessionFactory().openSession();
        SQLQuery sqlQuery = s.createSQLQuery(sql);
        sqlQuery.setParameter("user_code",userCode);
        List<User> list = sqlQuery.addEntity(User.class).list();
        return list.get(0);*/
    }

}
