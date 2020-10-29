package oyh.web;


import oyh.pojo.user;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Filter1 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        user user= (oyh.pojo.user) request.getSession().getAttribute("user");
        if(user==null)
        {
            String msg="请先登录";
            request.setAttribute("msg",msg);
           request.getRequestDispatcher("/pages/user/login_fail.jsp").forward(req,resp);

        }
        else {
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
