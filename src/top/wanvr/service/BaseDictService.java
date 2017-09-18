package top.wanvr.service;

import top.wanvr.entity.BaseDict;

import java.util.List;

public interface BaseDictService {

    //根据type_code查询字典对象
    List<BaseDict> getListByTypeCode(String dict_type_code);
}
