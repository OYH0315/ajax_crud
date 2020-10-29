package oyh.web;


import oyh.pojo.user;
import oyh.service.impl.userserviceimpl;
import oyh.service.userservice;
import oyh.utils.webutils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        Cookie[] cookies = request.getCookies();//获取cookie数组
        if(webutils.findcookie(cookies,"loginuser")!=null )//利用工具查找是否存在名为loginuser得cookie
        {
            userservice userservice=new userserviceimpl();
            user user1=userservice.exituser(webutils.findcookie(cookies,"loginuser").getValue());//从数据库中获取用户信息
            if(user1!=null)
            {
                request.getSession().setAttribute("user",user1);//将用户信息保存到sessio域中
                request.getRequestDispatcher("/pages/client/main2.jsp").forward(req,resp);
            }
        }

            else {
                request.getRequestDispatcher("/pages/client/main2.jsp").forward(req,resp);
            }
            }




    public void init(FilterConfig config) throws ServletException {

    }

}
