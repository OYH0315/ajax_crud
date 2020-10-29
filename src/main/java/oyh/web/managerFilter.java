package oyh.web;


import oyh.pojo.user;
import oyh.service.impl.user_roleserviceimpl;
import oyh.service.user_roleservice;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class managerFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        user user = (oyh.pojo.user) request.getSession().getAttribute("user");
        if(user!=null)
        {
            int id=user.getId();
            user_roleservice service=new user_roleserviceimpl();//通过service查询数据库获得当前登录用户的权限信息
            int roleid = service.getroleid(id);
            if(roleid==1)//为普通用户
            {
            request.setAttribute("msg","当前用户不具有该权限");//将错误信息通过发送给给浏览器
                request.getRequestDispatcher("/pages/user/login_fail.jsp").forward(req,resp);//请求转发到错误界面；
                return;

            }
            else if(roleid==2)//管理元用户则继续当前操作
            {
                chain.doFilter(req, resp);
                return;
            }


        }
        //若未登录
        request.setAttribute("msg","请先登录");
        request.getRequestDispatcher("/pages/user/login_fail.jsp").forward(req,resp);//请求转发到错误界面；

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
