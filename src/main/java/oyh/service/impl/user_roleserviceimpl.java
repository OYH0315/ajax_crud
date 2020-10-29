package oyh.service.impl;


import oyh.dao.impl.user_roledaoimpl;
import oyh.dao.user_roledao;
import oyh.service.user_roleservice;

public class user_roleserviceimpl implements user_roleservice {
    @Override
    public int getroleid(int userid) {
        user_roledao dao=new user_roledaoimpl();
        return  dao.getroleid(userid);
    }
}
