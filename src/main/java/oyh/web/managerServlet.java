package oyh.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import oyh.pojo.Tuser;
import oyh.pojo.downloadlist;
import oyh.service.UserManagerService;
import oyh.service.downloadlistServicce;
import oyh.service.impl.downloadlistServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Controller
public class managerServlet extends BaseServlet {

@Autowired
 UserManagerService service;
    public void showdownpage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        downloadlistServicce downloadlistServicce=new downloadlistServiceimpl();
        List<downloadlist> list=downloadlistServicce.querylist();
        request.setAttribute("list",list);
        request.getRequestDispatcher("/pages/manager/down_manager.jsp").forward(request,response);
    }
    public void showduserpage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        {
//            PageHelper.startPage(1,5);
            List<Tuser> list=service.getall();
            for(Tuser tuser:list)
            {
                System.out.println(tuser.toString());
            }
//            PageInfo pageInfo=new PageInfo(emps,5);
//            System.out.println(pageInfo.getPages());
//            request.setAttribute("pageinfo",pageInfo);
          request.getRequestDispatcher("/pages/manager/user_manager.jsp").forward(request,response);
        }
    }

}
