package top.wanvr.service;

import org.hibernate.criterion.DetachedCriteria;
import top.wanvr.entity.Customer;
import top.wanvr.utils.PageBean;

public interface CustomerService {
    //分页业务方法
    PageBean getPageBean(DetachedCriteria customer, Integer currentPage, Integer pageSize);
    //保存客户
    void save(Customer customer);
}
