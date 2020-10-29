package oyh.pojo;

public class user_role {
    private int userid;
    private int id;
    private String roleid;

    public user_role() {
    }

    public user_role(int userid, int id, String roleid) {
        this.userid = userid;
        this.id = id;
        this.roleid = roleid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "user_role{" +
                "userid=" + userid +
                ", id=" + id +
                ", roleid='" + roleid + '\'' +
                '}';
    }
}
