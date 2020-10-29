package oyh.web;


import com.google.gson.Gson;
import oyh.pojo.user;
import oyh.service.codeservice;
import oyh.service.impl.userserviceimpl;
import oyh.service.userservice;
import oyh.utils.webutils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class UserServlet extends BaseServlet {
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("login"));
        HashMap<String,Object> map=new HashMap<>();
        System.out.println(request.getParameter("username"));
        System.out.println("**********"+request.getParameter("login"));
        user user= webutils.copyparamtobean(request.getParameterMap(), new user());
        userservice userservice=new userserviceimpl();
        System.out.println(user.toString());
        String code1= request.getParameter("code");
        String code= (String) request.getSession().getAttribute("vcode");
        String msg=null;
        if(code.equalsIgnoreCase(code1))
        {
            if(userservice.loginuser(user)!=null)
            {
               user user2=userservice.exituser(user.getUsername());
               request.getSession().setAttribute("user",user2);
                System.out.println("登陆成功");
                map.put("flag",1);
                if(!(request.getParameter("login").equals("false")))
                {
                    System.out.println(5645646);
                    Cookie cookie=new Cookie("loginuser",user.getUsername());
                    cookie.setMaxAge(60*108);//设置cookie存活时间为一周
                    response.addCookie(cookie);

                }

            }
            else {
                msg="账号或者密码错误";
                map.put("flag",2);
                map.put("msg",msg);

            }
        }
    else{
        msg="验证码错误";
        map.put("flag",2);
        map.put("msg",msg);
        }
    response.getWriter().write(new Gson().toJson(map));
    }
    protected void createcode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        codeservice codeservice=new codeservice();
        String code=codeservice.createCode();
        request.getSession().setAttribute("vcode",code);
        response.setContentType("img/jpeg");
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);
        BufferedImage image=codeservice.createimg(code);
        ServletOutputStream out=response.getOutputStream();
        ImageIO.write(image,"JPEG",out);
        System.out.println("458453416574896451564");
        out.flush();
        out.close();

    }
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException//安全退出
    {
        HttpSession session = request.getSession();
        session.invalidate();
        Cookie[] cookies = request.getCookies();//获取浏览器cookie
        if(webutils.findcookie(cookies,"loginuser")!=null)
        {
            Cookie cookie=webutils.findcookie(cookies,"loginuser");
            cookie.setMaxAge(0);//删除cookie
            response.addCookie(cookie);
            request.getRequestDispatcher("/pages/client/main2.jsp").forward(request,response);
        }
        else
        {
            System.out.println(789789);
            request.getRequestDispatcher("/pages/client/main2.jsp").forward(request,response);
        }


    }
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userservice userservice=new userserviceimpl();
//        String username= request.getParameter("username");
//        String password= request.getParameter("password");
//        String email= request.getParameter("email");
        String code1= request.getParameter("code");
        String code= (String) request.getSession().getAttribute("vcode");
        user us1=  webutils.copyparamtobean(request.getParameterMap(),new user());
        us1.setProvince(request.getParameter("province").substring(0,request.getParameter("province").indexOf("/")));
        us1.setCity(request.getParameter("city").substring(0,request.getParameter("city").indexOf("/")));
        System.out.println(us1.toString());
        String msg="";
        HashMap<String,Object> map=new HashMap<>();
        System.out.println(code1+code);
        if(code1.equalsIgnoreCase(code))
        {
            if(userservice.checkuser(us1.getUsername())==true&&userservice.checkuser2(us1.getEmail())==true)
            {

                userservice.registuser(us1);
                user user2=userservice.exituser(us1.getUsername());
                request.getSession().setAttribute("user",user2);
                map.put("flag",2);
            }
            else
                {
                map.put("flag",1);
                if(userservice.checkuser2(us1.getEmail())==false)
                    {
                        msg=msg+"邮箱已经存在";
                    }
                    if(userservice.checkuser(us1.getUsername())==false)
                    {
                        msg=msg+"用户名已存在";
                    }
                    map.put("msg",msg);
            }
        }
        else {
            msg="验证码错误";
            map.put("flag",1);
            map.put("msg",msg);
        }
        response.getWriter().write(new Gson().toJson(map));
    }
}
