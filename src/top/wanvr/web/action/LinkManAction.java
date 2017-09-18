package top.wanvr.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import top.wanvr.entity.LinkMan;
import top.wanvr.service.LinkManService;
import top.wanvr.utils.PageBean;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

    LinkMan linkMan = new LinkMan();

    private LinkManService lms;

    private Integer currentPage;
    private Integer pageSize;

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

    public LinkManService getLms() {
        return lms;
    }

    public void setLms(LinkManService lms) {
        this.lms = lms;
    }

    //查询分页
    public String list() throws Exception {
        System.out.println(111111);
        //封装离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
        if (StringUtils.isNotBlank(linkMan.getLkm_name())) {
            dc.add(Restrictions.like("lkm_name", "%" + linkMan.getLkm_name() + "%"));
        }
        if(linkMan.getCustomer()!=null) {
            //这里是翻页后对象创建了，但未提交ID判断
            if(linkMan.getCustomer().getCust_id() != null) {
                dc.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
            }
        }
        //调用Service查询分页数据（PageBean）
        PageBean pb = lms.getPageBean(dc, currentPage, pageSize);
        //将PageBean放入request域，转发到列表页面显示
        ActionContext.getContext().put("pageBean", pb);
        ActionContext.getContext().put("lkm_cut",linkMan.getCustomer());
        return "list";
    }

    //添加联系人
    public String add() throws Exception{
        lms.saveOrUpdate(linkMan);
        return "toList";
    }

    @Override
    public LinkMan getModel() {
        return linkMan;
    }
}
