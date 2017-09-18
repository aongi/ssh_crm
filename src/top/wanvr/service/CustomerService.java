package top.wanvr.service;

import org.hibernate.criterion.DetachedCriteria;
import top.wanvr.entity.Customer;
import top.wanvr.utils.PageBean;

public interface CustomerService {
    //分页业务方法
    PageBean getPageBean(DetachedCriteria customer, Integer currentPage, Integer pageSize);
    //保存或修改客户
    void saveOrUpdate(Customer customer);

    //根据ID获得客户对象
    Customer getById(Long cust_id);
}
