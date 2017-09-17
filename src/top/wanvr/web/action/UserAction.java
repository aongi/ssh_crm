package top.wanvr.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import top.wanvr.entity.User;
import top.wanvr.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User>{
    private UserService us;
    private User user = new User();

    public UserService getUs() {
        return us;
    }

    public void setUs(UserService us) {
        this.us = us;
    }

    //action请求测试方法
    public String login() throws Exception {
        //1.调用Service执行登陆逻辑
        User u = us.getUserByCodePassword(user);
        //2.将返回的User对象放到Session域中
        ActionContext.getContext().getSession().put("User",u);
        //重定向到项目首页
        return "toHome";
    }

    @Override
    public User getModel() {
        return user;
    }
}
