package oyh.service.impl;


import oyh.dao.impl.userdaoimpl;
import oyh.dao.userdao;
import oyh.pojo.user;
import oyh.service.userservice;

import java.util.List;

public class userserviceimpl implements userservice {
    private userdao dao=new userdaoimpl();
    @Override
    public void registuser(user user) {
       dao.saveuser(user);
    }

    @Override
    public user loginuser(user user) {
       return dao.querybyusernameandpassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean checkuser(String username) {
        if(dao.queryuserbyusername(username)==null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean checkuser2(String email) {
        if(dao.querybyemail(email)==null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    @Override
    public user exituser(String username) {
        if(dao.queryuserbyusername(username)==null)
        {
            return  null;
        }
        else {
            return dao.queryuserbyusername(username);
        }
    }

    @Override
    public List<user> query() {
        return dao.queryfoelist();
    }
}
