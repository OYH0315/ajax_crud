package oyh.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import oyh.dao.TuserMapper;
import oyh.pojo.Tuser;
import oyh.pojo.TuserExample;
import oyh.service.UserManagerService;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class springtest {
    @Autowired
    TuserMapper mapper;
    @Autowired
    UserManagerService service;
@Test
    public void testcrud()
{
int id=0;
String username="adasdasasdad";
String email="";
String province="";

    TuserExample example=new TuserExample();
    TuserExample.Criteria criteria1=example.createCriteria();
    if(id!=0)
    {
        criteria1.andIdLike("%"+id+"%");
    }
    if(username!="")
    {
        criteria1.andUsernameLike("%"+username+"%");
    }
    if(email!="")
    {
        criteria1.andEmailLike("%"+email+"%");
    }
    if(province!="")
    {
        criteria1.andProvinceLike("%"+province+"%");
    }

example.or(criteria1);
    PageHelper.startPage(1,5);
    List<Tuser> tusers = mapper.selectByExample(example);
    PageInfo infp=new PageInfo(tusers,5);
    System.out.println(tusers.size());
    System.out.println(infp.getPages());
}
@Test
    public void testinsert()
{
    Tuser tuser = new Tuser(null, "2222", "2222", "2222", "222", "2222");
    int i = mapper.insertSelective(tuser);
    System.out.println("*********"+i);
}
@Test
    public void testupdate()
{
    Tuser tuser = new Tuser(16, "adminss", null, "564567", "上海", "芜湖");
    int i = mapper.updateByPrimaryKeySelective(tuser);
    System.out.println("*********"+i);
}
@Test
    public void testlist()
{
    List<Integer> lists=new ArrayList<>();
    lists.add(7);
    TuserExample example=new TuserExample();
    TuserExample.Criteria criteria=example.createCriteria();
    criteria.andIdIn(lists);
    mapper.deleteByExample(example);
}
@Test
    public void testselect()
{
    TuserExample example=new TuserExample();
    TuserExample.Criteria criteria=example.createCriteria();
    criteria.andUsernameEqualTo("9889");
    example.or(criteria);
    List<Tuser> list=new ArrayList<>();
    list=null;
    list= mapper.selectByExample(example);
    if(list.size()==0)
    {
        System.out.println("8888888888888");
    }

}
}