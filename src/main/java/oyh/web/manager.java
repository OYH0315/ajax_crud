package oyh.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import oyh.pojo.Tuser;
import oyh.pojo.msg;
import oyh.service.UserManagerService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class manager {
    @Autowired
    UserManagerService service;

    @RequestMapping("/jsonuser")
    @ResponseBody
    public msg getuserwithjson(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        PageHelper.startPage(pn, 5);
        List<Tuser> tuserList = service.getall();
        PageInfo pageInfo = new PageInfo(tuserList, 5);
        return msg.success().add("pageinfo", pageInfo);
    }

    @RequestMapping("/query")
    @ResponseBody
    public msg getuserbylike
            (@RequestParam(value = "pn", defaultValue = "1") Integer pn,
             @RequestParam(value = "id", defaultValue = "0") Integer id,
             @RequestParam(value = "email", defaultValue = "def") String email,
             @RequestParam(value = "province", defaultValue = "def") String province,
             @RequestParam(value = "username", defaultValue = "def") String username,
             @RequestParam(value = "flag", defaultValue = "0") Integer flag,
             Model model) {
        System.out.println(id + username + email + province);
        PageHelper.startPage(pn, 5);
        List<Tuser> tusers = service.querybylike(id, username, email, province, flag);
        if(tusers.size()==0)
        {
            return msg.fail();
        }
        else
        {
            PageInfo pageInfo = new PageInfo(tusers, 5);
            return msg.success().add("pageinfo", pageInfo);
        }

    }

    @RequestMapping("/insert")
    @ResponseBody
    public msg insertuser(Tuser tuser) {
        String username = tuser.getUsername();
        if (service.checkusername(username) == null) {
            int i = service.saveuser(tuser);
            if (i == 1) {
                return msg.success().add("flag",1);
            } else {
                return msg.fail().add("flag",1);
            }
        }
        else
        {
            return msg.fail().add("flag",0);
        }
    }

    @RequestMapping("/returnuser")
    @ResponseBody
    public msg getuser(@RequestParam(value = "id", defaultValue = "0") Integer id) {
        Tuser tuser = service.getuserbyid(id);
        return msg.success().add("userinfo", tuser);
    }

    @RequestMapping("/update")
    @ResponseBody
    public msg updateuser(Tuser tuser) {
        String username = tuser.getUsername();
        if (service.checkusername(username) == null) {
            int i = service.updateuserbyid(tuser);
            if (i == 1) {
                return msg.success().add("flag",1);
            } else {
                return msg.fail().add("flag",1);
            }
        }
        else
        {
            return msg.fail().add("flag",0);
        }

    }


@RequestMapping("/delete")
@ResponseBody
    public msg deletebyid(@RequestParam(value = "id",defaultValue = "0") Integer id)
{
    int i = service.deleteuser(id);
        if(i>=1)

    return msg.success();
        else
            return msg.fail();
}
@RequestMapping("/deletelist")
    @ResponseBody
    public msg deletelists(@RequestParam(value = "ids") String ids)
{
    List<Integer> idlists=new ArrayList<>();
    String[] str=ids.split("-");
    for(String s:str)
    {
        idlists.add(Integer.parseInt(s));
    }
    System.out.println();
    int i = service.deletelists(idlists);
    if(i>0)
    {
        return msg.success();
    }
    else
    {
        return msg.fail();
    }

}
}
