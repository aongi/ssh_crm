package top.wanvr.service;

import org.hibernate.criterion.DetachedCriteria;
import top.wanvr.entity.LinkMan;
import top.wanvr.utils.PageBean;

public interface LinkManService {

    //保存联系人
    void saveOrUpdate(LinkMan linkMan);


    //联系人分页
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
}
