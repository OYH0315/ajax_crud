package oyh.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.Cookie;
import java.util.Map;

public class webutils {
    public static  <T> T copyparamtobean(Map value, T bean)
    {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
    public static Cookie findcookie(Cookie[] cookies,String name)
    {
        if(cookies.length==0||name==null||cookies==null)
        {
            return  null;
        }
        for(Cookie cookie:cookies)
        {
            if(name.equalsIgnoreCase(cookie.getName()))
            {
                return  cookie;
            }
        }
        return  null;
    }
    public static int parseint(String strint,int defaultvalue)
    {
        try {
            return Integer.parseInt(strint);
        }
        catch (Exception e)
        {
//e.printStackTrace();
        }
        return defaultvalue;

    }
}
