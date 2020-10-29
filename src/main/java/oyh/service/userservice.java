package oyh.service;



import oyh.pojo.user;

import java.util.List;

public interface userservice {
    public void registuser(user user);
    public user loginuser(user user);
    public boolean checkuser(String username);
   public boolean checkuser2(String email);
    public user exituser(String username);
    public List<user> query();
}
