package top.wanvr.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * 通过BaseDao实现增删改查代码解耦
 * */
public interface BaseDao<T> {
    //增或修改
    void saveOrUpdate(T t);

    //增
    void save(T t);
    //删
    void delete(T t);
    void delete(Serializable id);
    //改
    void update(T t);

    //根据ID查询（所有基本数据类型都实现了序列化接口)
    T getById(Serializable id);
    //查询总记录数(通过离线查询条件查询)
    Integer getTotalCount(DetachedCriteria dc);
    //查询分页列表数据
    List<T> getPageList(DetachedCriteria dc,Integer start,Integer pageSize);
}
