package top.wanvr.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import top.wanvr.entity.Customer;
import top.wanvr.service.CustomerService;
import top.wanvr.utils.PageBean;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private Customer customer = new Customer();

    private CustomerService cs;

    private Integer currentPage;
    private Integer pageSize;

    public CustomerService getCs() {
        return cs;
    }

    public void setCs(CustomerService cs) {
        this.cs = cs;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    //查询分页
    public String list() throws Exception {
        //封装离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
        if(StringUtils.isNotBlank(customer.getCust_name())){
            dc.add(Restrictions.like("cust_name","%"+customer.getCust_name()+"%"));
        }

        //调用Service查询分页数据（PageBean）
        PageBean pb = cs.getPageBean(dc, currentPage, pageSize);
        //将PageBean放入request域，转发到列表页面显示
        ActionContext.getContext().put("pageBean", pb);
        return "list";
    }

    @Override
    public Customer getModel() {
        return customer;
    }
}