package oyh.service;


import oyh.pojo.downloadlist;

import java.util.List;

public interface downloadlistServicce {
    public List<downloadlist> querylist();
    public downloadlist querforone(int id);
}
