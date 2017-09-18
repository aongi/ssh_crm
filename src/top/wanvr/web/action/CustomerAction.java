package top.wanvr.web.action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import top.wanvr.entity.Customer;
import top.wanvr.service.CustomerService;
import top.wanvr.utils.PageBean;

import java.io.File;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private Customer customer = new Customer();

    private CustomerService cs;

    private Integer currentPage;
    private Integer pageSize;


    //接收上传的图片
    private File photo;

    //接收上传的图片名称(在提交的键名后加上FileName即可)
    private String photoFileName;
    private String photoContextType;//文件MIME类型

    public String getPhotoContextType() {
        return photoContextType;
    }

    public void setPhotoContextType(String photoContextType) {
        this.photoContextType = photoContextType;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

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
        if (StringUtils.isNotBlank(customer.getCust_name())) {
            dc.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
        }

        //调用Service查询分页数据（PageBean）
        PageBean pb = cs.getPageBean(dc, currentPage, pageSize);
        //将PageBean放入request域，转发到列表页面显示
        ActionContext.getContext().put("pageBean", pb);
        return "list";
    }

    //添加客户
    public String add() throws Exception {
        //文件上传
        if(photo != null) {
            photo.renameTo(new File(ServletActionContext.getRequest().getContextPath() + "/upload/" + photoFileName));
        }
        //---------------------------------
        //调用service，保存Customer对象
        cs.saveOrUpdate(customer);
        //重定向到Action
        return "toList";

    }

    //修改客户
    public String toEdit() throws Exception {
        //调用Service根据Id获得客户对象
        Customer c = cs.getById(customer.getCust_id());
        //将客户对象放置到request域，并转发到编辑页面
        ActionContext.getContext().put("customer",c);
        return "edit";
    }

    @Override
    public Customer getModel() {
        return customer;
    }
}
