package top.wanvr.service.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.wanvr.dao.CustomerDao;
import top.wanvr.entity.Customer;
import top.wanvr.service.CustomerService;
import top.wanvr.utils.PageBean;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {


    private CustomerDao cd;

    @Override
    //分页业务方法
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {

        //调用Dao查询总记录数
        Integer totalCount = cd.getTotalCount(dc);
        //创建PageBean对象
        PageBean pb = new PageBean(currentPage,totalCount,pageSize);
        //调用Dao查询分页列表数据
        List<Customer> list = cd.getPageList(dc,pb.getStart(),pb.getPageSize());
        //列表数据放入PageBean中，并返回
        pb.setList(list);
        return pb;
    }

    //保存或修改客户
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ ,propagation = Propagation.REQUIRED)
    public void saveOrUpdate(Customer customer) {
        //维护Customer与数据字典对象的关系,Struts2已经帮我们封装了
        //调用Dao保存客户
        cd.saveOrUpdate(customer);
    }

    //根据ID获得客户对象
    @Override
    public Customer getById(Long cust_id) {
        return cd.getById(cust_id);
    }

    public CustomerDao getCd() {
        return cd;
    }

    public void setCd(CustomerDao cd) {
        this.cd = cd;
    }
}
