package top.wanvr.web.action;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import top.wanvr.entity.BaseDict;
import top.wanvr.service.BaseDictService;

import java.util.List;

public class BaseDictAction extends ActionSupport {

    public String getDict_type_code() {
        return dict_type_code;
    }

    public void setDict_type_code(String dict_type_code) {
        this.dict_type_code = dict_type_code;
    }

    private String dict_type_code;

    private BaseDictService baseDictService;

    public BaseDictService getBaseDictService() {
        return baseDictService;
    }

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }



    @Override
    public String execute() throws Exception {

        //调用Service根据typeCode获取数据字典对象list
        List<BaseDict> list= baseDictService.getListByTypeCode(dict_type_code);
        //将list转换为json格式
        Gson gson = new Gson();
        String json = gson.toJson(list);
        //将Json发送给浏览器
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");//指定格式json
        ServletActionContext.getResponse().getWriter().write(json);

        return null;//告诉Struts2不需要结果处理
    }
}
