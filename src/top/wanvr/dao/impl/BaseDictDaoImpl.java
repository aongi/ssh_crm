package top.wanvr.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import top.wanvr.dao.BaseDictDao;
import top.wanvr.entity.BaseDict;

import java.util.List;

public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {

    //根据type_code查询字典对象
    @Override
    public List<BaseDict> getListByTypeCode(String dict_type_code) {

        //使用Criteria查询
        //创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(BaseDict.class);
        //封装条件
        dc.add(Restrictions.eq("dict_type_code",dict_type_code));
        //执行查询
        List<BaseDict> list = (List<BaseDict>) getHibernateTemplate().findByCriteria(dc);
        return list;
    }
}
