package oyh.test;

import oyh.dao.impl.userdaoimpl;
import oyh.dao.userdao;

public class userdaoTest {

    @org.junit.Test
    public void queryuserbyusername() {
        userdao dao=new userdaoimpl();
        System.out.println(dao.queryuserbyusername("admin").toString());
    }

    @org.junit.Test
    public void saveuser() {
    }

    @org.junit.Test
    public void querybyusernameandpassword() {
    }

    @org.junit.Test
    public void queryfoelist() {
    }

    @org.junit.Test
    public void querybyemail() {
    }
}