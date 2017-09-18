package top.wanvr.service.impl;

import top.wanvr.dao.BaseDictDao;
import top.wanvr.entity.BaseDict;
import top.wanvr.service.BaseDictService;

import java.util.List;

public class BaseDictServiceImpl implements BaseDictService {

    public BaseDictDao getBdd() {
        return bdd;
    }

    public void setBdd(BaseDictDao bdd) {
        this.bdd = bdd;
    }

    private BaseDictDao bdd;

    //根据type_code查询字典对象
    @Override
    public List<BaseDict> getListByTypeCode(String dict_type_code) {
        return bdd.getListByTypeCode(dict_type_code);
    }
}
