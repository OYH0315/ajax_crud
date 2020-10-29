package oyh.dao.impl;


import oyh.dao.user_roledao;

public class user_roledaoimpl  extends basedao implements user_roledao {
    @Override
    public int getroleid(int userid) {
        String sql="select roleid from t_role_user where userid=?";
        return (int) queryforsinger(sql,userid);
    }
}
