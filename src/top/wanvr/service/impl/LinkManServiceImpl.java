package top.wanvr.service.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.wanvr.dao.LinkManDao;
import top.wanvr.entity.LinkMan;
import top.wanvr.service.LinkManService;
import top.wanvr.utils.PageBean;

import java.util.List;

public class LinkManServiceImpl implements LinkManService {

    private LinkManDao lmd;

    public LinkManDao getLmd() {
        return lmd;
    }

    public void setLmd(LinkManDao lmd) {
        this.lmd = lmd;
    }
    @Override
    //分页业务方法
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {

        //调用Dao查询总记录数
        Integer totalCount = lmd.getTotalCount(dc);
        //创建PageBean对象
        PageBean pb = new PageBean(currentPage,totalCount,pageSize);
        //调用Dao查询分页列表数据
        List<LinkMan> list = lmd.getPageList(dc,pb.getStart(),pb.getPageSize());
        //列表数据放入PageBean中，并返回
        pb.setList(list);
        return pb;
    }



    //添加联系人
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public void saveOrUpdate(LinkMan linkMan) {
        lmd.saveOrUpdate(linkMan);
    }
}
