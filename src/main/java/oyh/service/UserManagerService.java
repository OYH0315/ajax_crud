package oyh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oyh.dao.TuserMapper;
import oyh.pojo.Tuser;
import oyh.pojo.TuserExample;

import java.util.List;
@Service
public class UserManagerService {
    //注入mapper
@Autowired
    TuserMapper mapper;
public List<Tuser> getall()
{
    return mapper.selectByExample(null);//返回所有用户信息
}
    public List<Tuser> querybylike(Integer id,String username,String email,String province ,int flag )//模糊查询返回用户信息
    {
        TuserExample example=new TuserExample();
        TuserExample.Criteria criteria1=example.createCriteria();
        if(id!=0)
        {
            criteria1.andIdLike("%"+id+"%");
        }
        if(!username.equals("def"))
        {
            criteria1.andUsernameLike("%"+username+"%");
        }
        if(!email.equals("def"))
        {
            criteria1.andEmailLike("%"+email+"%");
        }
        if(!province.equals("def"))
        {
            criteria1.andProvinceLike("%"+province+"%");
        }
        if(flag==1)
        {
            example.setOrderByClause("id desc");
        }
        example.or(criteria1);
        return mapper.selectByExample(example);
    }

    public int saveuser(Tuser tuser) {
    return mapper.insertSelective(tuser);
    }
    public Tuser getuserbyid(Integer id)
    {
        return mapper.selectByPrimaryKey(id);
    }
    public int updateuserbyid(Tuser tuser)
    {
        int i = mapper.updateByPrimaryKeySelective(tuser);
        return i;
    }
    public int deleteuser(Integer id)
    {
        int i = mapper.deleteByPrimaryKey(id);
        return  i;
    }
    public int deletelists(List<Integer> list)
    {
        TuserExample example=new TuserExample();
        TuserExample.Criteria criteria=example.createCriteria();
         criteria.andIdIn(list);
         return  mapper.deleteByExample(example);
    }
public Tuser checkusername(String username)
{
    TuserExample example=new TuserExample();
    TuserExample.Criteria criteria=example.createCriteria();
    criteria.andUsernameEqualTo(username);
    example.or(criteria);
    List<Tuser> list = mapper.selectByExample(example);
    if(list.size()==0)
    {
        return null;
    }
    else {
        return list.get(0);
    }
}
}
