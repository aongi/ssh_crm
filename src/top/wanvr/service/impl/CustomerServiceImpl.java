package top.wanvr.service.impl;

import org.hibernate.criterion.DetachedCriteria;
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

    public CustomerDao getCd() {
        return cd;
    }

    public void setCd(CustomerDao cd) {
        this.cd = cd;
    }
}